package practice.interview.bst;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import practice.interview.bst.BST.TreeNode;

public class TreeSerializer {
    public String serialize(final BST bst) {
        TreeNode root = bst.getRoot();
        Queue<TreeNode> treeNodes = new LinkedList();
        List<Integer> serialized = new ArrayList<>();
        treeNodes.add(root);
        while(!treeNodes.isEmpty()) {
            TreeNode node = treeNodes.poll();
            serialized.add(node.val);
            if(node.left != null) treeNodes.add(node.left);
            if(node.right != null) treeNodes.add(node.right);
        }

        return StringUtils.join(serialized, ",");
    }

    public BST deserialize(final String serialized) {
        BST bst = new BST();
        List<Integer> values = Arrays.asList(serialized.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        values.forEach(value -> bst.add(value));
        return bst;
    }

    public static void main(String[] args) {
        TreeSerializer treeSerializer = new TreeSerializer();
        BSTTraversal bstTraversal = new BSTTraversal();

        BST bst = BST.getFullBalancedTree();
        List<Integer> before = bstTraversal.inorderTraversal(bst);

        String serialized = treeSerializer.serialize(bst);
        BST deserializedBST = treeSerializer.deserialize(serialized);

        List<Integer> after = bstTraversal.inorderTraversal(deserializedBST);

        System.out.println("Before: " + before.toString());
        System.out.println("After: " + after.toString());
    }
}
