package practice.interview.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable> {
    Node root = null;

    void add(T value) {
        if(root == null) {
            root = new Node<T>(value);
        } else {
            root.add(value);
        }
    }

    public void BreadthFirstRetrieve() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.value);
            if(node.getRight() != null)
                queue.add(node.getRight());

            if(node.getLeft() != null)
                queue.add(node.getLeft());
        }
    }

    public void DepthFirstRetrieve() {
        Stack<Node> stack = new Stack<Node>();
        stack.add(root);

        while(!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.value);
            if(node.getRight() != null)
                stack.push(node.getRight());

            if(node.getLeft() != null)
                stack.push(node.getLeft());
        }
    }

    class Node<T extends Comparable> {
        T value;
        Node left;
        Node right;

        public Node(T value) {
            this.value = value;
            left = null;
            right = null;
        }

        public Node getRight() {
            return right;
        }

        public Node getLeft() {
            return left;
        }

        public void add(Node<T> node) {
            int compared = node.value.compareTo(this.value);
            if( compared > 0) {
                setRight(node);
            } else if(compared < 0){
                setLeft(node);
            }
        }

        public void add(T value) {
            int compared = value.compareTo(this.value);
            if( compared > 0) {
                setRight(new Node<>(value));
            } else if(compared < 0){
                setLeft(new Node<>(value));
            }
        }

        public void setLeft(Node<T> node) {
            if(left == null) {
                left = node;
            } else {
                left.add(node);
            }
        }

        public void setRight(Node<T> node) {
            if(right == null) {
                right = node;
            }
            else {
                right.add(node);
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(10);
        bst.add(5);
        bst.add(9);
        bst.add(3);
        bst.add(15);
        bst.add(19);
        bst.add(13);

        bst.BreadthFirstRetrieve();
        bst.DepthFirstRetrieve();
    }
}
