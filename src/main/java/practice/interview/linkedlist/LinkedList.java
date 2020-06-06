package practice.interview.linkedlist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList<T extends Comparable>  {
    private final static int MAX_CAPACITY = 1024;

    private Node head = null;
    private Node tail = null;

    private int size = 0;

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size > 0 ? true : false;
    }

    public boolean contains(Object o) {
        boolean found = false;

        if(o instanceof Comparable) {
            Node ptr = head;
            while(!found && ptr != null) {
                if(ptr.get().compareTo(o) == 0) {
                    found = true;
                }
                ptr = ptr.getNext();
            }
        }
        return found;
    }

    public Iterator<T> iterator() {
        return null;
    }

    public Object[] toArray() {
        Object[] arr = new Object[this.size];

        int index = 0;
        Node ptr = head;

        while(ptr != null) {
            arr[index++] = ptr.get();
            ptr = ptr.getNext();
        }

        return arr;
    }

    public boolean add(T t) {
        if(this.size >= MAX_CAPACITY) {
            return false;
        }

        Node<T> node = new Node<>(t);
        tail = node;

        if(head != null) {
            head.setNext(node);
        } else {
            head = node;
        }

        return true;
    }

    public boolean remove(T o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }

    public T get(int index) {
        return null;
    }

    public T set(int index, T element) {
        return null;
    }

    public void add(int index, T element) {

    }

    public T remove(int index) {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<T> listIterator() {
        return null;
    }

    public ListIterator<T> listIterator(int index) {
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    public void find(int i) {
        if(i == 45) {
            return;
        } else {
            i = i + 1;
            find(i);
        }
    }

    public static void main(String[] args) {
        int index = 0;
        LinkedList<String> list = new LinkedList<>();
        list.find(index);
        System.out.println(index);
    }
}