package practice.interview.stack;

import practice.interview.doublelinkedlist.DoubleLinkedList;
import practice.interview.doublelinkedlist.DoubleLinkedNode;

public class StackWithLinkedList<T extends Comparable> {
    DoubleLinkedList list = new DoubleLinkedList<T>();

    public void push(T value) {
        list.append(value);
    }

    public T pop() {
        DoubleLinkedNode<T> tail = list.popTail();
        return tail != null ? tail.get() : null;
    }

    public Integer size() {
        return list.size();
    }
}
