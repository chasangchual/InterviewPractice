package practice.interview.facebook.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstTraversal {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(100);
        tree.add(50);
        tree.add(200);
        tree.add(25);
        tree.add(75);
        tree.add(350);

        Stack<TreeNode> nodes = new Stack<>();
        nodes.add(tree.getRoot());
        while(!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            System.out.println(node);
            if(node.hasRight()) {
                nodes.add(node.getRight());
            }
            if(node.hasLeft()) {
                nodes.add(node.getLeft());
            }
        }
    }
}
