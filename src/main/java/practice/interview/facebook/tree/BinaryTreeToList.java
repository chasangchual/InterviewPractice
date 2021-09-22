package practice.interview.facebook.tree;

import java.util.*;

public class BinaryTreeToList {
    Node root = null;

    public void add(Integer value) {
        if(Objects.isNull(root)) {
            root = new Node(value) ;
        } else {
            Node parent = root;
            Node curr = root.getValue() > value ? root.getLeft() : root.getRight();

            while(Objects.nonNull(curr)) {
                parent = curr;
                curr = curr.getValue() > value ? curr.getLeft() : curr.getRight();
            }

            parent.add(value);
        }
    }

    public void printBreathFirstSearch() {
        Queue<Node> queue = new LinkedList<>();
        if(Objects.nonNull(root)) {
            queue.add(root);
            printBreathFirstSearch(queue);
        }
    }

    public void printBreathFirstSearch(Queue<Node> queue) {
        while (Boolean.FALSE.equals(queue.isEmpty())) {
            Node node = queue.poll();
            System.out.println(node.getValue());

            if(Objects.nonNull(node.getLeft())) {
                queue.add(node.getLeft());
            }

            if(Objects.nonNull(node.getRight())) {
                queue.add(node.getRight());
            }
            printBreathFirstSearch(queue);
        }
    }

    public void printDepthFirstSearch() {
        Stack<Node> stack = new Stack<>();
        if(Objects.nonNull(root)) {
            stack.push(root);
            printDepthFirstSearch(stack);
        }
    }

    public void printDepthFirstSearch(Stack<Node> stack) {
        while (Boolean.FALSE.equals(stack.isEmpty())) {
            Node node = stack.pop();
            System.out.println(node.getValue());

            if(Objects.nonNull(node.getRight())) {
                stack.push(node.getRight());
            }

            if(Objects.nonNull(node.getLeft())) {
                stack.push(node.getLeft());
            }
            printDepthFirstSearch(stack);
        }
    }

    public Integer findMin() {
        return findMin(root).getValue();
    }

    public Node findMin(Node node) {
        if(Objects.nonNull(node.getLeft())) {
            return findMin(node.getLeft());
        } else {
            return node;
        }
    }

    public void toList() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        toList(stack);
    }

    public void toList(Stack<Node> stack) {

        Node node = stack.pop();
        if(node.hasLeft()) {
            stack.push(node.getLeft());
            toList(stack);
        }

        System.out.println(node.getValue());

        if(node.hasRight()) {
            stack.push(node.getRight());
            toList(stack);
        }
    }

    class Node {
        Integer value;
        Node left;
        Node right;

        public Node(Integer value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Boolean hasLeft() {
            return Objects.nonNull(this.left);
        }

        public Boolean hasRight() {
            return Objects.nonNull(this.right);
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node node) {
            this.left = node;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node node) {
            this.right = node;
        }

        public Integer getValue() {
            return value;
        }

        public Node add(Integer value) {
            Node node = new Node(value);

            if(value > this.value) {
                setRight(node);
            } else {
                setLeft(node);
            }
            return node;
        }

        @Override
        public String toString() {
            return this.value.toString();
        }
    }

    public static void main(String... args) {
        BinaryTreeToList tree = new BinaryTreeToList();
        tree.add(100);
        tree.add(50);
        tree.add(200);
        tree.add(25);
        tree.add(75);
        tree.add(125);
        tree.add(350);
        tree.add(30);
        tree.add(60);

        // tree.printBreathFirstSearch();
        // tree.printDepthFirstSearch();

        // System.out.println(tree.findMin());
        tree.toList();
    }
}
