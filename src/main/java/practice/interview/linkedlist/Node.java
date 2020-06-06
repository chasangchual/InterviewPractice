package practice.interview.linkedlist;

public class Node<T extends Comparable> {
    private T value;
    private Node next = null;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node next) {
        this.value = value;
        this.next = next;
    }

    public T get() {
        return value;
    }

    public void set(T t) {
        value = t;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
