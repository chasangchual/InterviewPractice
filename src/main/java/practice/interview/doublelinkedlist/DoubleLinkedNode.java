package practice.interview.doublelinkedlist;

public class DoubleLinkedNode<T extends Comparable> {
    T value;
    DoubleLinkedNode next = null;
    DoubleLinkedNode prev = null;

    public DoubleLinkedNode(final T value) {
        this.value = value;
    }

    public DoubleLinkedNode(final T value, final DoubleLinkedNode prev , final DoubleLinkedNode next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void setNext(final DoubleLinkedNode next) {
        this.next = next;
    }

    public DoubleLinkedNode getNext() {
        return next;
    }

    public void setPrev(final DoubleLinkedNode prev) {
        this.prev = prev;
    }

    public DoubleLinkedNode getPrev() {
        return prev;
    }

    public boolean equals(T compare) {
        return value.compareTo(compare) == 0;
    }
}
