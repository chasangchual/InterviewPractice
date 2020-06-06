package practice.interview.linkedlist;

public class ReverseLinkedListIteratively {
    public Node<Integer> reverse(Node<Integer> head) {
        Node prev = head ;
        Node curr = head.getNext();

        head.setNext(null);

        while(curr != null) {
            Node temp = curr;
            curr = curr.getNext();

            temp.setNext(prev);
            prev = temp;
        }
        return prev;
    }

    static public void traverse(Node node) {
        if(node != null) {
            System.out.println(node.get().toString());
            traverse(node.getNext());
        }
    }


    public static void main(String[] args) {
        Node<Integer> head = new Node<>(0);
        head.setNext(new Node(1));
        head.getNext().setNext(new Node(2));
        head.getNext().getNext().setNext(new Node(3));
        head.getNext().getNext().getNext().setNext(new Node(4));

        //ReverseLinkedList.traverse(head);

        ReverseLinkedListIteratively reverseLinkedList = new ReverseLinkedListIteratively();
        ReverseLinkedListIteratively.traverse(reverseLinkedList.reverse(head));
    }
}
