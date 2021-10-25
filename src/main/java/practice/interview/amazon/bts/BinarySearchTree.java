package practice.interview.amazon.bts;

import java.util.*;

public class BinarySearchTree {
    Node root = null;

    public Node getRoot() {
        return this.root;
    }

    public Node add(Integer data) {
        Node node = null;

        if(Objects.isNull(root)) {
            this.root = new Node(data);
            return root;
        } else {
            Node curr = this.root;
            while(Objects.nonNull(curr)) {
                if(data > curr.getData()) {
                    if(Objects.isNull(curr.getRight())) {
                        node = new Node(data);
                        curr.setRight(node);
                    }
                    curr = curr.getRight();
                } else if(data < curr.getData()) {
                    if(Objects.isNull(curr.getLeft())) {
                        node = new Node(data);
                        curr.setLeft(node);
                    }
                    curr = curr.getLeft();
                } else {
                    return curr;
                }
            }
        }
        return node;
    }

    public List<Node> dfs() {
        List<Node> nodes = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        if(Objects.isNull(root)) {
            return nodes;
        }

        stack.add(root);
        while(Boolean.FALSE.equals(stack.isEmpty())) {
            Node node = stack.pop();
            nodes.add(node);

            if(Objects.nonNull(node.getRight())) {
                stack.push(node.getRight());
            }

            if(Objects.nonNull(node.getLeft())) {
                stack.push(node.getLeft());
            }
        }

        return nodes;
    }

    public List<Node> bfs() {
        List<Node> nodes = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();

        if(Objects.isNull(root)) {
            return nodes;
        }

        queue.add(root);
        while(Boolean.FALSE.equals(queue.isEmpty())) {
            Node node = queue.poll();
            nodes.add(node);

            if(Objects.nonNull(node.getLeft())) {
                queue.add(node.getLeft());
            }

            if(Objects.nonNull(node.getRight())) {
                queue.add(node.getRight());
            }
        }

        return nodes;
    }

    public List<Node> inorder() {
        List<Node> nodes = new ArrayList<>();

        if(Objects.isNull(root)) {
            return nodes;
        }

        inorder(nodes, root);
        return nodes;
    }

    private void inorder(List<Node> nodes, Node curr) {
        if(Objects.isNull(curr)) {
            return;
        }

        inorder(nodes, curr.getLeft());
        nodes.add(curr);
        inorder(nodes, curr.getRight());
    }

    public List<Node> preorder() {
        List<Node> nodes = new ArrayList<>();

        if(Objects.isNull(root)) {
            return nodes;
        }

        preorder(nodes, root);
        return nodes;
    }

    private void preorder(List<Node> nodes, Node curr) {
        if(Objects.isNull(curr)) {
            return;
        }

        nodes.add(curr);
        preorder(nodes, curr.getLeft());
        preorder(nodes, curr.getRight());
    }

    public List<Node> postorder() {
        List<Node> nodes = new ArrayList<>();

        if(Objects.isNull(root)) {
            return nodes;
        }

        postorder(nodes, root);
        return nodes;
    }

    private void postorder(List<Node> nodes, Node curr) {
        if(Objects.isNull(curr)) {
            return;
        }

        postorder(nodes, curr.getLeft());
        postorder(nodes, curr.getRight());
        nodes.add(curr);
    }
}
