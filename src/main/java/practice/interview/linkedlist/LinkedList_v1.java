package practice.interview.linkedlist;

public class LinkedList_v1<T extends Comparable> {
    Node_v1 head = null;
    Node_v1 tail = null;
    Integer count = 0;

    public final Node_v1 getHead() {
        return head;
    }

    public final Node_v1 getTail() {
        return tail;
    }

    public void append(T value) {
        if(head != null) {
            Node_v1 node = new Node_v1<T>(value);
            tail.setNext(node);
            tail = node;
        } else {
          tail = head = new Node_v1<T>(value);
        }
        count ++;
    }

    public void printList() {
        Node_v1 ptr = head;
        while(ptr != null) {
            System.out.println(ptr.get().toString());
            ptr = ptr.getNext();
        }
    }

    public Node_v1<T> popTail() {
        if(head != null) {
            Node_v1 ptr = tail;

            tail = getPrecedent(tail);
            tail.setNext(null);

            count --;

            return ptr;
        }

        return null;
    }

    public Node_v1<T> popHead() {
        if(head != null) {
            Node_v1 ptr = head;
            head = ptr.getNext();

            count --;

            return ptr;
        }

        return null;
    }

    private Node_v1<T> getPrecedent(final Node_v1 node) {
        if (node != null) {
            Node_v1 ptr = head;
            while(ptr != null && ptr.getNext() != node) {
                ptr = ptr.getNext();
            }
            return ptr;
        }
        return null;
    }

    public Node_v1 search(final T value) {
        Node_v1 ptr = head;
        while(ptr != null && !ptr.equals(value)) {
            ptr = ptr.getNext();
        }
        return ptr;
    }

    public Node_v1 search(final Node_v1 node) {
        Node_v1 ptr = head;
        while(ptr != null && ptr != node) {
            ptr = ptr.getNext();
        }
        return ptr;
    }

    public Node_v1 search(final T value, final Node_v1 node) {
        if(search(node) != null) {
            Node_v1 ptr = node;
            while(ptr != null && !ptr.equals(value)) {
                ptr = ptr.getNext();
            }
            return ptr;
        }
        return null;
    }

    public void insertAfter(final T value, final Node_v1 node) {
        if(search(node) != null) {
            if(node == tail) {
                append(value);
            } else {
                Node_v1 newNode = new Node_v1<T>(value);
                newNode.setNext(node.getNext());
                node.setNext(newNode);
                count ++;
            }
        }
    }

    public void insertBefore(final T value, final Node_v1 node) {
        if(search(node) != null) {
            if(node == head) {
                Node_v1 newNode = new Node_v1<T>(value);
                newNode.setNext(head);
                head = newNode;
            } else {
                Node_v1 newNode = new Node_v1<T>(value);
                Node_v1 pre = getPrecedent(node);

                newNode.setNext(node.getNext());
                pre.setNext(newNode);
            }
            count ++;
        }
    }

    public void appendToHead(final T value) {
        insertBefore(value, head);
    }

    public Node_v1 delete(final Node_v1 node) {
        if(search(node) != null) {
            Node_v1 pre = getPrecedent(node);
            pre.setNext(node.getNext());

            count --;

            return node;
        }
        return null;
    }

    public Node_v1 delete(final T value) {
        Node_v1 found = search(value);
        if(found != null) {
            Node_v1 pre = getPrecedent(found);
            pre.setNext(found.getNext());
            count --;
            return found;
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedList_v1 list = new LinkedList_v1<String>();

        list.append("a");
        list.append("b");
        list.append("c");
        list.append("d");
        list.append("e");

        list.printList();
        System.out.println("-----------------");

        Node_v1 head = list.popHead();
        list.printList();
        System.out.println(".................");
        System.out.println(head.get());
        System.out.println("-----------------");

        Node_v1 tail = list.popTail();
        list.printList();
        System.out.println(".................");
        System.out.println(tail.get());
        System.out.println("-----------------");

        Node_v1 found = list.search("c");
        System.out.println(found.get() + " : " +  found);
        System.out.println("-----------------");

        list.append("c");
        list.printList();
        System.out.println("-----------------");

        found = list.search("c", found.getNext());
        System.out.println(found.get() + " : " +  found);
        System.out.println("-----------------");


        tail = list.popTail();
        list.append("e");
        found = list.search("c");
        list.insertAfter("x", found);
        list.printList();
        System.out.println("-----------------");

        list.delete("x");
        list.printList();
        System.out.println("-----------------");

        list.appendToHead("a");
        list.printList();
        System.out.println("-----------------");
    }
}
