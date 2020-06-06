package practice.interview;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class SimpleTree {
    Node root = null ;

    public Node add(final int value) {
        Node node = null;

        if(root != null) {
            node = add(root, value);
        } else {
            node = new Node(value);
            root = node ;
        }

        return node;
    }

    public Node add(Node node, final int value) {
        if(value == node.getValue()) {
            return node;
        } else if(value < node.getValue()) {
            if(node.getLeft() == null) {
                Node newNode = new Node(value);
                node.setLeft(newNode);
                return node;
            } else {
                return add(node.getLeft(), value);
            }

        } else {
            if(node.getRight() == null) {
                Node newNode = new Node(value);
                node.setRight(newNode);
                return node;
            } else {
                return add(node.getRight(), value);
            }
        }
    }

    public List<Integer> find(final int value) {
        List<Integer> values = new ArrayList<Integer>();

        if(root != null) {
            if(root.getValue() == value) {
                values.add(root.getValue());
            } else if(value < root.getValue()) {
                find(values, root.getLeft(), value) ;
            } else {
                find(values, root.getRight(), value) ;
            }
        }

        return values;
    }

    public void find(List<Integer> values, Node node, final int value) {
        if(node != null) {
            if(node.getValue() == value) {
                values.add(node.getValue());
            } else if(value < node.getValue()) {
                find(values, root.getLeft(), value) ;
            } else {
                find(values, root.getRight(), value) ;
            }
        }
    }

    public static void main(String[] args) {
        SimpleTree solution = new SimpleTree();

        solution.add(1);
        System.out.println(Arrays.toString(solution.find(1).toArray()));

        solution.add(3);
        System.out.println(Arrays.toString(solution.find(3).toArray()));

        solution.add(5);
        System.out.println(Arrays.toString(solution.find(5).toArray()));

    }

    class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public void setValue(final int value) {
            this.value = value;
        }

        public void setLeft(final Node node) {
            this.left = node;
        }

        public void setRight(final Node node) {
            this.right = node;
        }

        public int getValue() {
            return this.value;
        }

        public Node getLeft() {
            return this.left;
        }

        public Node getRight() {
            return this.right;
        }
    }
}

