package practice.interview.queue;

import practice.interview.doublelinkedlist.DoubleLinkedList;
import practice.interview.doublelinkedlist.DoubleLinkedNode;

public class QueueWithLinkedList<T extends Comparable> {
    DoubleLinkedList list = new DoubleLinkedList<T>();

    public void push(T value) {
        list.append(value);
    }

    public T pop() {
        DoubleLinkedNode<T> node = list.getHead();
        list.deleteHead();
        return node.get();
    }
}