package practice.interview.agoda;

public class QueueWIthArray {
    private static int INITIAL_SIZE = 5;
    private static int EXPAND_SIZE = 5;

    int[] values = new int[INITIAL_SIZE];
    int head = -1;
    int tail = -1;

    public void push(int v) {
        if(tail >= (values.length-1)) {
            expand();
        }

        values[++tail] = v;

        if(head == -1) {
            head = tail;
        }
    }

    public int pop() {
        int pop = values[head];
        head ++;
        return pop;
    }

    public int size() {
        if(tail == -1) return 0;
        return tail - head + 1;
    }

    private void expand() {
        int[] temp = new int[values.length + EXPAND_SIZE];
        System.arraycopy(values, 0, temp, 0, values.length);
        this.values = temp;
        temp = null;
    }

    public void print() {
        for(int i = head; i <= tail; i++) {
            System.out.println(values[i]);
        }
    }

    public static void main(String[] args) {
        QueueWIthArray queue = new QueueWIthArray();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        queue.push(6);
        queue.push(7);
        queue.print();

        System.out.println(queue.size());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.size());

        System.out.println("-------------------------------");
        queue.push(8);
        queue.push(9);
        queue.push(10);
        queue.push(11);
        queue.push(12);
        queue.push(13);
        queue.push(14);
        queue.print();
    }
}
