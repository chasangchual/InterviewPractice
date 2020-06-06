package practice.interview.linkedlist;

public class Node_v1<T extends Comparable> {
    T value;
    Node_v1 next = null;

    public Node_v1(final T value) {
        this.value = value;
    }

    public Node_v1(final T value, final Node_v1 next) {
        this.value = value;
        this.next = next;
    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void setNext(final Node_v1 next) {
        this.next = next;
    }

    public Node_v1 getNext() {
        return next;
    }

    public boolean equals(T compare) {
        return value.compareTo(compare) == 0;
    }
}
