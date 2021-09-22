package practice.interview.facebook.list;

import java.util.List;
import java.util.Objects;

public class LinkedList {
    Node head = null;
    Integer size = 0;

    public Node findLast() {
        if (Objects.isNull(head)) {
            return head;
        }

        Node curr = head;
        while(curr.hasNext()) {
            curr = curr.getNext();
        }
        return curr;
    }

    public void add(Integer value) {
        if(head != null) {
            Node last = findLast();
            last.setNext(new Node(value));
        } else {
            head = new Node(value);
        }
        size ++;
    }

    public Integer get(Integer pos) {
        if(head == null) {
            throw new IndexOutOfBoundsException();
        }

        Node curr = head;
        Integer index = 0;
        while (Objects.nonNull(curr) && (index < pos)) {
            curr = curr.getNext();
            if(curr != null) {
                index ++;
            }
        }

        if (index < pos && Objects.isNull(curr)) {
            throw new IndexOutOfBoundsException();
        }

        if (index == pos && Objects.nonNull(curr)) {
            return curr.getValue();
        }
        throw new IllegalStateException();
    }

    public static void main(String... args) {
        LinkedList list = new LinkedList();
        try {
            System.out.println(list.get(0));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("good to have IndexOutOfBoundsException");
        }

        list.add(1);
        System.out.println(list.get(0));
        try {
            System.out.println(list.get(1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("good to have IndexOutOfBoundsException");
        }

        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
    }

    class Node {
        Integer value;
        Node next;

        public Node(Integer value) {
            this.value = value;
            this.next = null;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Integer getValue() {
            return value;
        }

        public boolean hasNext() {
            return Objects.nonNull(next);
        }
    }
}
