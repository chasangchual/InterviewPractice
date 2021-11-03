package practice.interview.amazon.stack;

import java.util.Objects;

public class Stack<E extends Comparable<E>> {
    Node<E> head = null;
    Node<E> tail = null;
    E min = null;

    public void push(E data) {
        if(Objects.nonNull(head)) {
            Node<E> node = new Node<>(data);

            tail.setNext(node);
            tail = node;

            if(min.compareTo(data) > 0) {
                min = data;
            }

        } else {
            head = tail = new Node<>(data);
            min = data;
        }
    }

    public E pop() {
        if(Objects.nonNull(head)) {
            Node<E> node = tail;

            node.getPrev().setNext(null);
            tail = tail.getPrev();

            return node.get();

        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public E getMin() {
        return min;
    }

    public String print() {
        String out = Objects.isNull(min) ? "" : "min : " + min.toString() + "   |";

        Node ptr = head;
        while(Objects.nonNull(ptr)) {
            out = out + " >> " + ptr.get().toString();
            ptr = ptr.getNext();
        }

        return out;
    }

    class Node <E> {
        E data;
        Node prev;
        Node next;

        public Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;

            if(Objects.nonNull(next)) {
                this.next.setPrev(this);
            }
        }

        public E get() {
            return data;
        }

        public void setNext(Node next) {
            this.next = next;
            if(Objects.nonNull(next)) {
                this.next.prev = this;
            }
        }

        public Node getNext() {
            return this.next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;

            if (Objects.nonNull(prev)) {
                prev.next = next;
            }
        }

        public Node getPrev() {
            return this.prev;
        }
    }
}
