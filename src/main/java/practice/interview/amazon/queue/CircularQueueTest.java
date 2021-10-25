package practice.interview.amazon.queue;

public class CircularQueueTest {
    public static void main(String[] args) {
        CircularQueue<Integer> queue = new CircularQueue<>();

        queue.push(2);
        queue.push(4);
        queue.push(6);
        queue.push(8);
        queue.push(10);

        System.out.println(queue.pop());
        System.out.println(queue.pop());

        queue.push(11);
        queue.push(12);
        queue.push(13);
        queue.push(14);
        queue.push(15);
        queue.push(16);
        queue.push(17);
        queue.push(18);
        queue.push(19);

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());

        queue.push(21);
        queue.push(22);
        queue.push(23);
        queue.push(24);
        queue.push(25);
        queue.push(26);
        queue.push(27);
        queue.push(28);
        queue.push(29);

        queue.push(31);
        queue.push(32);
        queue.push(33);
        queue.push(34);
        queue.push(35);
        queue.push(36);
        queue.push(37);
        queue.push(38);
        queue.push(39);
    }
}
