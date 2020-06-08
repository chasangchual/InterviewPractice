package practice.interview.bst;

public class BST {
    TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public void add(int val) {
        if(root == null) {
            root = new TreeNode(val);
        } else {
            add(root, val);
        }
    }

    private void add(TreeNode node, int val) {
        if(node != null) {
            if(val > node.val) {
                if(node.right == null) {
                    node.right = new TreeNode(val);
                } else {
                    add(node.right, val);
                }
            } else if(val < node.val) {
                if(node.left == null) {
                    node.left = new TreeNode(val);
                } else {
                    add(node.left, val);
                }
            }
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
