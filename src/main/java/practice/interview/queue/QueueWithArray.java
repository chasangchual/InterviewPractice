package practice.interview.queue;

public class QueueWithArray<T extends Comparable> {
    private static final int CAPACITY = 10;
    T[] a = (T[]) new Object[CAPACITY];

    int head = -1;
    int tail = -1;
    int size = 0;

    public void push(T value) {
        if(size >= CAPACITY) {
            throw new IllegalStateException("no space available");
        }

        if(tail >= (CAPACITY - 1)) {
            shift();
        }

        a[++tail] = value;
        size ++;

        if(size <= 0) {
            head = tail;
        }
    }

    public T pop() {
        size --;
        return a[head++];
    }

    private void shift() {
        for(int i = head; i <= tail; i++) {
            a[i - head] = a[i];
        }
        head = 0;
        tail = size -1;
    }
}
