package practice.interview.amazon.bts;

public class Node {
    Integer data;
    Node left;
    Node right;

    public Node (final Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public Node (final Integer data, final Node left, final Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Integer getData() {
        return data;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setLeft(final Node node) {
        this.left = node;
    }

    public void setRight(final Node node) {
        this.right = node;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
