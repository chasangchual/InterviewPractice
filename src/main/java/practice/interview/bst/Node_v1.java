package practice.interview.bst;

public class Node_v1<T extends Comparable<T>> implements Comparable<Node_v1<T>> {
    private Node_v1 left = null;
    private Node_v1 right = null;
    private T value;

    public Node_v1(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node_v1 getLeft() {
        return this.left;
    }

    public Node_v1 getRight() {
        return this.right;
    }

    public void setLeft(Node_v1 nodeV1) {
        this.left = nodeV1;
    }

    public void setRight(Node_v1 nodeV1) {
        this.right = nodeV1;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    @Override
    public int compareTo(Node_v1<T> o) {
        return value.compareTo(o.getValue());
    }

    public int compareTo(T o) {
        return value.compareTo(o);
    }
}
