package practice.interview.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BubbleSort {
    public static void doSort(final int items[]) {
        for(int k = items.length - 1; k >= 0; k--) {
            for(int i = 0; i < k; i++) {
                if(items[i] > items[i+1]) {
                    swap(items, i, i + 1);
                }
            }
        }

        System.out.println(Arrays.toString(items));
    }

    private static void swap(final int items[], int from, int to) {
        int temp = items[from];
        items[from] = items[to];
        items[to] = temp;
    }

    public static void main(String[] args) {
        BubbleSort.doSort(new int[]{3, 5 ,4, 6, 7, 8, 9, 1, 2, 4, 0});
    }
}
