package practice.interview.agoda;

import java.lang.reflect.Array;
import java.util.Objects;

public class ArrayToList {
    public static Node toList(int[] a) {
        Node head = null;
        Node tail = null;

        if(a.length <= 0) return null;

        if(a.length > 1) {
            head = new Node(a[0]);
            tail = head;

            for(int i = 1 ; i < a.length; i++) {
                Node node = new Node(a[i]);
                tail.setNext(node);
                tail = node;
            }
        } else {
            head = new Node(a[0]);
        }
        return head;
    }

    public static void printList(Node head) {
        Node curr = head;
        while(Objects.nonNull(curr)) {
            System.out.println(curr.getValue());
            curr = curr.getNext();
        }
    }

    public static void addAfter(Node head, int order, int value) {
        int pos = 0;
        Node curr = head;

        while(order > pos && Objects.nonNull(curr)) {
            pos ++;
            curr = curr.getNext();
        }

        Node node = new Node(value);
        node.setNext(curr.getNext());
        curr.setNext(node);
    }

    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }

        public void setNext(Node node) {
            this.next = node;
        }

        public int getValue() {
            return this.value;
        }

        public Node getNext() {
            return this.next;
        }
    }

    public static void main(String[] args) {
        ArrayToList.Node head = ArrayToList.toList(new int[]{7, 9, 8, 1, 2, 4, 5});
        ArrayToList.printList(head);

        ArrayToList.addAfter(head, 2, 6);
        ArrayToList.printList(head);
    }
}
