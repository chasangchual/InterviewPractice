package practice.interview.bst;

import practice.interview.bst.BST.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BSTTraversal {
    public List<Integer> inorderTraversal(BST bst) {
        List<Integer> result = new ArrayList<>();

        if(bst.getRoot() != null) {
            Stack<TreeNode> stack = new Stack<>();

            TreeNode node = bst.getRoot();

            while(!stack.isEmpty() || node != null) {
                if(node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    node = stack.pop();
                    result.add(node.val);
                    node = node.right;
                }
            }
        }

        return result;
    }

    public List<Integer> inorderTraversalRecursive(BST bst) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalRecursive(result, bst.getRoot());
        return result;
    }

    public void inorderTraversalRecursive(List<Integer> result, TreeNode node) {
        if(node == null) {
            return;
        }
        inorderTraversalRecursive(result, node.left);
        result.add(node.val);
        inorderTraversalRecursive(result, node.right);
    }

    public List<Integer> preorderTraversal(BST bst) {
        List<Integer> result = new ArrayList<>();

        if(bst.getRoot() != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(bst.getRoot());

            while(!stack.empty()) {
                TreeNode node = stack.pop();
                result.add(node.val);
                if(node.right != null) stack.push(node.right);
                if(node.left != null) stack.push(node.left);
            }
        }
        return result;
    }

    public List<Integer> preorderTraversalRecursive(BST bst) {
        List<Integer> result = new ArrayList<>();
        if(bst.getRoot() != null) {
            preorderTraversalRecursive(result, bst.getRoot());
        }
        return result;
    }

    public void preorderTraversalRecursive(List<Integer>result, TreeNode node) {
        if(node == null) return;

        result.add(node.val);
        preorderTraversalRecursive(result, node.left);
        preorderTraversalRecursive(result, node.right);
    }

    public static void main(String[] args) {
        BSTTraversal bstTraversal = new BSTTraversal();

        BST bst = BST.getFullBalancedTree();

        System.out.println("Inorder Iterative: " + bstTraversal.inorderTraversal(bst));
        System.out.println("Inorder Recursive: " + bstTraversal.inorderTraversalRecursive(bst));

        System.out.println("Preorder Iterative : " + bstTraversal.preorderTraversal(bst));
        System.out.println("Preorder Recursive: " + bstTraversal.preorderTraversalRecursive(bst));

        bst = BST.getFullBalancedTree();

        System.out.println("Inorder Iterative: " + bstTraversal.inorderTraversal(bst));
        System.out.println("Inorder Recursive: " + bstTraversal.inorderTraversalRecursive(bst));

        System.out.println("Preorder Iterative: " + bstTraversal.preorderTraversal(bst));
        System.out.println("Preorder Recursive: " + bstTraversal.preorderTraversalRecursive(bst));
    }
}
