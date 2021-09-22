package practice.interview.facebook.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.add(100);
        tree.add(50);
        tree.add(200);
        tree.add(25);
        tree.add(75);
        tree.add(350);

        TreeNode root = tree.getRoot();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {

            List<TreeNode> nodes = new ArrayList<>();

            while(!queue.isEmpty()) {
                nodes.add(queue.poll());
            }

            System.out.println(nodes.toString());

            for(TreeNode node: nodes) {
                if(node.hasLeft()) {
                    queue.add(node.getLeft());
                }
                if(node.hasRight()) {
                    queue.add(node.getRight());
                }
            }
        }
    }
}
