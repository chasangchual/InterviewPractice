package practice.interview.doublelinkedlist;

/**
 * double link linked list
 * @param <T> Class that implements Comparable interface
 */
public class DoubleLinkedList<T extends Comparable> {
    DoubleLinkedNode head = null;
    DoubleLinkedNode tail = null;
    Integer size = 0;

    /**
     * returns header node
     * @return header node
     */
    public final DoubleLinkedNode getHead() {
        return head;
    }

    /**
     * returns tail node
     * @return tail node
     */
    public final DoubleLinkedNode getTail() {
        return tail;
    }

    public Integer size() {
        return size;
    }

    /**
     * append a new node after tail node
     * @param value
     */
    public void append(T value) {
        if(head != null) {
            DoubleLinkedNode doubleLinkedNode = new DoubleLinkedNode<T>(value);
            // make double linked
            tail.setNext(doubleLinkedNode);
            doubleLinkedNode.setPrev(tail);

            tail = doubleLinkedNode;
        } else { // for the first node
          tail = head = new DoubleLinkedNode<T>(value);
        }
        size++;
    }

    /**
     * print out all nodes
     */
    public void printList() {
        DoubleLinkedNode ptr = head;
        while(ptr != null) {
            System.out.println(ptr.get().toString());
            ptr = ptr.getNext();
        }
    }

    /**
     * returns the tail node and remove it from the list.
     * @return tail node
     */
    public DoubleLinkedNode<T> popTail() {
        if(head != null) { // if the list has node(S)
            DoubleLinkedNode ptr = tail;

            tail = getPrecedent(tail);
            tail.setNext(null);
            size--;

            return ptr;
        }

        return null;
    }

    /**
     * returns the head node and remove it from the list
     * @return head node
     */
    public DoubleLinkedNode<T> popHead() {
        if(head != null) { // if the list has node(S)
            DoubleLinkedNode ptr = head;
            head = ptr.getNext();
            size--;

            return ptr;
        }

        return null;
    }

    /**
     * returns precedent doubleLinkedNode of the given doubleLinkedNode.
     * it returns null if the given doubleLinkedNode is null.
     * @param node current doubleLinkedNode
     * @return precedent doubleLinkedNode.
     */
    private DoubleLinkedNode<T> getPrecedent(final DoubleLinkedNode node) {
        if (node != null) {
            return node.getPrev();
        }
        return null;
    }

    /**
     * searches the given value in the list starting from the head.
     * It returns the found node if it the value is found. Otherwise it returns null
     * @param value value to search
     * @return found node
     */
    public DoubleLinkedNode search(final T value) {
        DoubleLinkedNode ptr = head;
        while(ptr != null && !ptr.equals(value)) {
            ptr = ptr.getNext();
        }
        return ptr;
    }

    /**
     * searches the given doubleLinkedNode in the list starting from the head.
     * It returns the found doubleLinkedNode if it the doubleLinkedNode is found. Otherwise it returns null
     * @param node doubleLinkedNode to search
     * @return found doubleLinkedNode
     */
    public DoubleLinkedNode search(final DoubleLinkedNode node) {
        DoubleLinkedNode ptr = head;
        while(ptr != null && ptr != node) {
            ptr = ptr.getNext();
        }
        return ptr;
    }

    /**
     * searches the given value in the list starting from the given doubleLinkedNode.
     * It returns the found doubleLinkedNode if it the value is found. Otherwise it returns null
     * @param value value to search
     * @param node doubleLinkedNode to start searching
     * @return found doubleLinkedNode
     */
    public DoubleLinkedNode search(final T value, final DoubleLinkedNode node) {
        if(search(node) != null) {
            DoubleLinkedNode ptr = node;
            while(ptr != null && !ptr.equals(value)) {
                ptr = ptr.getNext();
            }
            return ptr;
        }
        return null;
    }

    /**
     * inserts a new value to the list after the given doubleLinkedNode
     * @param value new value to add
     * @param node precedent doubleLinkedNode
     */
    public void insertAfter(final T value, final DoubleLinkedNode node) {
        if(search(node) != null) {
            if(node == tail) {
                append(value);
            } else {
                DoubleLinkedNode newDoubleLinkedNode = new DoubleLinkedNode<T>(value);
                // link subsequent of the new doubleLinkedNode
                node.getNext().setPrev(newDoubleLinkedNode);
                newDoubleLinkedNode.setNext(node.getNext());

                // link precedent of the new doubleLinkedNode
                newDoubleLinkedNode.setPrev(node);
                node.setNext(newDoubleLinkedNode);

                size++;
            }
        } else {
            throw new IllegalArgumentException("give doubleLinkedNode does not exist in the list.");
        }
    }

    /**
     * inserts a new value to the list before the given doubleLinkedNode
     * @param value new value to add
     * @param node subsequent doubleLinkedNode
     */
    public void insertBefore(final T value, final DoubleLinkedNode node) {
        if(search(node) != null) {
            if(node == head) {
                DoubleLinkedNode newDoubleLinkedNode = new DoubleLinkedNode<T>(value);
                newDoubleLinkedNode.setNext(head); // link subsequent of the doubleLinkedNode
                head.setPrev(newDoubleLinkedNode); // link precedent of the doubleLinkedNode

                head = newDoubleLinkedNode; // set a new head doubleLinkedNode
            } else {
                DoubleLinkedNode newDoubleLinkedNode = new DoubleLinkedNode<T>(value);
                DoubleLinkedNode pre = getPrecedent(node);

                // link subsequent of the doubleLinkedNode
                newDoubleLinkedNode.setNext(node.getNext());
                node.setPrev(newDoubleLinkedNode);

                // link precedent of the doubleLinkedNode
                newDoubleLinkedNode.setPrev(pre);
                pre.setNext(newDoubleLinkedNode);
            }
            size++;
        } else {
            throw new IllegalArgumentException("give doubleLinkedNode does not exist in the list.");
        }
    }

    /**
     * add a new node before the head node, and make the node as head
     * @param value new value to insert
     */
    public void appendToHead(final T value) {
        insertBefore(value, head);
    }

    /**
     * deletes the given doubleLinkedNode from the list
     * @param node doubleLinkedNode to delete
     * @return removed doubleLinkedNode. if the doubleLinkedNode does not exists
     */
    public DoubleLinkedNode delete(final DoubleLinkedNode node) {
        if(search(node) != null) {
            DoubleLinkedNode pre = getPrecedent(node);
            pre.setNext(node.getNext());
            size--;
            return node;
        }
        return null;
    }

    /**
     * delete the head node of the list
     * @return removed head node. returns null if the list is empty
     */
    public DoubleLinkedNode deleteHead() {
        if(this.head != null) {
            DoubleLinkedNode<T> head = this.head;
            this.head = head.getNext();
            head.getNext().setPrev(null);
            size--;
            return head;
        }
        return null;
    }

    /**
     * delete the tail node of the list
     * @return removed tail node
     */
    public DoubleLinkedNode deleteTail() {
        if(tail != null) {
            DoubleLinkedNode<T> tail = this.tail;
            this.tail = tail.getPrev();
            this.tail.setNext(null);
            size--;
            return tail;
        }
        return null;
    }

    public DoubleLinkedNode delete(final T value) {
        DoubleLinkedNode found = search(value);
        if(found != null) {
            DoubleLinkedNode pre = getPrecedent(found);
            pre.setNext(found.getNext());
            size--;
            return found;
        }
        return null;
    }

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList<String>();

        list.append("a");
        list.append("b");
        list.append("c");
        list.append("d");
        list.append("e");

        list.printList();
        System.out.println("-----------------");

        DoubleLinkedNode head = list.popHead();
        list.printList();
        System.out.println(".................");
        System.out.println(head.get());
        System.out.println("-----------------");

        DoubleLinkedNode tail = list.popTail();
        list.printList();
        System.out.println(".................");
        System.out.println(tail.get());
        System.out.println("-----------------");

        DoubleLinkedNode found = list.search("c");
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
