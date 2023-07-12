package practice.interview.sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class RadixSort {
    public static void soSort(final int[] items, int left, int right) {
        LinkedList<Integer>[] queues = new LinkedList[10];
        for(int i = 0 ; i < 10; i++) {
            queues[i] = new LinkedList<>();
        }

        int digitSum = 0;
        int figure = 1;

        do {
            digitSum = 0;
            for(int item : items) {
                int digit = (item / figure) % 10;
                digitSum += digit;
                queues[digit].addLast(item);
            }

            int pos = 0;
            for(int i = 0 ; i < 10; i++) {
                while(Boolean.FALSE.equals(queues[i].isEmpty())) {
                    items[pos++] = queues[i].pollFirst();
                }
            }
            figure *= 10;
        } while (digitSum > 0);

        System.out.println(Arrays.toString(items));
    }

    public static void main(String[] args) {
        RadixSort.soSort(new int[]{16, 80, 18, 77, 03, 24, 88, 23}, 0, 7);
        RadixSort.soSort(new int[]{215, 15, 344, 372, 294, 100, 8, 145, 24, 198, 831}, 0, 10);
    }
}
