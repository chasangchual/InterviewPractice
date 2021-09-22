package practice.interview.facebook.tree;

import java.util.LinkedList;
import java.util.Queue;

public class WidthFirstTraversal {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(100);
        tree.add(50);
        tree.add(200);
        tree.add(25);
        tree.add(75);
        tree.add(350);

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(tree.getRoot());
        while(!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            System.out.println(node);
            if(node.hasLeft()) {
                nodes.add(node.getLeft());
            }
            if(node.hasRight()) {
                nodes.add(node.getRight());
            }
        }
    }
}
