package practice.interview.amazon.queue;

public class CircularQueue<E> {
    public static Integer SIZE = 19;

    E[] circle = (E[]) new Object[SIZE];

    Integer head = -1;
    Integer tail = -1;

    public void push(E value) {
        if(isEmpty()) {
            head = 0;
            tail = 0;
            circle[0] = value;
            return;
        }

        if(isFull()) {
            throw new IndexOutOfBoundsException();
        }

        tail = (tail + 1) % SIZE;
        circle[tail] = value;
    }

    public E pop() {
        E data = null;

        if(isEmpty()) {
            return null;
        }

        if(head == tail) {
            data = circle[head];
            head = tail = -1;
        } else {
            data = circle[head];
            head = (head + 1) % SIZE;
        }

        return data;
    }

    private boolean isFull() {
        return !isEmpty() && ((tail + 1) % SIZE == head);
    }

    private boolean isEmpty() {
        return head == -1 && tail == -1;
    }
}
