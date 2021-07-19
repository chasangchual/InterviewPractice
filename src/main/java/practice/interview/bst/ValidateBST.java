package practice.interview.bst;

public class ValidateBST {
    public boolean isValidBSTRecursion(TreeNode root) {
        return isValidBSTRecursion(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTRecursion(TreeNode node, Long lower, Long upper) {
        if(node != null) {
            if(node.val <= lower || node.val >= upper) {
                return false;
            }
            return isValidBSTRecursion(node.right, Long.valueOf(node.val), upper) && isValidBSTRecursion(node.left, lower, Long.valueOf(node.val));
        }
        return true ;
    }

    public boolean isValidBSTIteration(TreeNode root) {
        return false;
    }

    public boolean isValidBSTInorder(TreeNode root) {
        return false;
    }

    public void inOrderTraverse(TreeNode node) {
        if(node == null) {
            return;
        }
        inOrderTraverse(node.left);
        System.out.println(node.val);
        inOrderTraverse(node.right);
    }

    public void inOrderTraverse() {

    }

    public static void add(TreeNode node, int val) {
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

    public static void main(String[] args) {
        ValidateBST validateBST = new ValidateBST();

        TreeNode root = new TreeNode(5);
        ValidateBST.add(root, 2);
        ValidateBST.add(root, 1);
        ValidateBST.add(root, 3);

        // System.out.println(validateBST.isValidBSTRecursion(root));

        System.out.println("----------------------------------");
        validateBST.inOrderTraverse(root);

        root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);

        root.right.right = new TreeNode(6);
        root.right.left = new TreeNode(3);

//        System.out.println(validateBST.isValidBSTRecursion(root));
        System.out.println("----------------------------------");
        validateBST.inOrderTraverse(root);

        root = new TreeNode(10);
        ValidateBST.add(root, 3);
        ValidateBST.add(root, 7);
        ValidateBST.add(root, 2);
        ValidateBST.add(root, 4);
        ValidateBST.add(root, 7);
        ValidateBST.add(root, 6);
        ValidateBST.add(root, 8);

        System.out.println("----------------------------------");
        validateBST.inOrderTraverse(root);

        //root = new TreeNode(2147483647);
        //System.out.println(validateBST.isValidBSTRecursion(root));
    }

    static class TreeNode {
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
