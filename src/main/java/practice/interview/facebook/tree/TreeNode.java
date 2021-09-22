package practice.interview.facebook.tree;

import java.util.Objects;

public class TreeNode {
    Integer value;
    TreeNode left;
    TreeNode right;

    public TreeNode(Integer value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Boolean hasLeft() {
        return Objects.nonNull(this.left);
    }

    public Boolean hasRight() {
        return Objects.nonNull(this.right);
    }

    public TreeNode getLeft() {
        return this.left;
    }

    public void setLeft(TreeNode node) {
        this.left = node;
    }

    public TreeNode getRight() {
        return this.right;
    }

    public void setRight(TreeNode node) {
        this.right = node;
    }

    public Integer getValue() {
        return value;
    }

    public TreeNode add(Integer value) {
        TreeNode node = new TreeNode(value);

        if(value > this.value) {
            setRight(node);
        } else {
            setLeft(node);
        }
        return node;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
