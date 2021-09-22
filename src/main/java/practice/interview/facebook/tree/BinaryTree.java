package practice.interview.facebook.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
    TreeNode root = null;

    public TreeNode getRoot() {
        return root;
    }

    public void add(Integer value) {
        if(Objects.isNull(root)) {
            root = new TreeNode(value) ;
        } else {
            TreeNode parent = root;
            TreeNode curr = root.getValue() > value ? root.getLeft() : root.getRight();

            while(Objects.nonNull(curr)) {
                parent = curr;
                curr = curr.getValue() > value ? curr.getLeft() : curr.getRight();
            }

            parent.add(value);
        }
    }

    public void printBreathFirstSearch() {
        Queue<TreeNode> queue = new LinkedList<>();
        if(Objects.nonNull(root)) {
            queue.add(root);
            printBreathFirstSearch(queue);
        }
    }

    public void printBreathFirstSearch(Queue<TreeNode> queue) {
        while (Boolean.FALSE.equals(queue.isEmpty())) {
            TreeNode node = queue.poll();
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
        Stack<TreeNode> stack = new Stack<>();
        if(Objects.nonNull(root)) {
            stack.push(root);
            printDepthFirstSearch(stack);
        }
    }

    public void printDepthFirstSearch(Stack<TreeNode> stack) {
        while (Boolean.FALSE.equals(stack.isEmpty())) {
            TreeNode node = stack.pop();
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

    public TreeNode findMin(TreeNode node) {
        if(Objects.nonNull(node.getLeft())) {
            return findMin(node.getLeft());
        } else {
            return node;
        }
    }

    public void toList() {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        toList(stack);
    }

    public void toList(Stack<TreeNode> stack) {

        TreeNode node = stack.pop();
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
}
