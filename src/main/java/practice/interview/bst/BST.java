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

    public static BST getFullBalancedTree() {
        BST bst = new BST();

        bst.add(20);
        bst.add(10);
        bst.add(3);
        bst.add(17);
        bst.add(2);
        bst.add(4);
        bst.add(16);
        bst.add(18);
        bst.add(30);
        bst.add(23);
        bst.add(37);
        bst.add(22);
        bst.add(24);
        bst.add(36);
        bst.add(38);

        return bst;
    }

    public static BST getSparseTree() {
        BST bst = new BST();

        bst = new BST();

        bst.add(20);
        bst.add(10);
        bst.add(3);
        bst.add(17);
        bst.add(16);
        bst.add(18);
        bst.add(30);
        bst.add(37);
        bst.add(36);

        return bst;
    }
}
