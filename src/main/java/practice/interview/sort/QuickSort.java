package practice.interview.sort;

import java.util.Arrays;

/**
 * https://tobinatore.github.io/algovis/quicksort.html
 */
public class QuickSort {
    public static void doSort(int[] values) {
        printArray(values);
        System.out.println("------> ");

        // quickSort(values, 0, values.length - 1);
        doQuickSort(0, 0, values.length - 1, values);
        System.out.println("***************");
    }

    public static void doQuickSort(int p, int l, int r, int[] values) {
        if(r > l) {
            if((r - l + 1) == 2) {
                if(values[l] > values[r]) {
                    swap(values, r, l);
                }

                printArray(values);

            } else {

                int pi = partition(l, r, values);

                printArray(values);

                // set the pivot index as the first item in the array
                doQuickSort(l, l, pi - 1, values);
                doQuickSort(pi+1, pi+1, r, values);
            }
        }
    }

    public static int partition(int from, int to, int[] values) {
        int rightBound = to - 1;
        int leftBound = from;
        int pivot = values[to]; // pick the last element as a pivot

        // run until the bounds are crossed
        while(rightBound > leftBound) {
            while(rightBound >= from && values[rightBound] >= pivot) {
                rightBound --;
            }

            while(leftBound <= (to - 1) && values[leftBound] <= pivot) {
                leftBound ++;
            }

            // if the both bounds are not crossed yet, do swap left and right
            // else, change the pivot and left bound
            if(rightBound > leftBound) {
                swap(values, leftBound, rightBound);
            } else {
                swap(values, leftBound, to);
            }
        }
        return leftBound;
    }

    private static void printArray(int[] values) {
        System.out.println(Arrays.toString(values));
    }
    private static void swap(int[] values, int from, int to) {
        if(from != to) {
            int temp = values[from];
            values[from] = values[to];
            values[to] = temp;
        }
    }

    public static void main(String[] args) {
        QuickSort.doSort(new int[]{5, 1, 8, 9, 7, 2, 4, 3, 11, 6, 10});
        // QuickSort.doSort(new int[]{3, 4});
        // QuickSort.doSort(new int[]{4, 3});

        QuickSort.doSort(new int[]{1, 2, 3});
        QuickSort.doSort(new int[]{2, 1, 3});
        QuickSort.doSort(new int[]{3, 2, 1});

        // QuickSort.doSort(new int[]{1, 2, 3, 4, 5});
        // QuickSort.doSort(new int[]{5, 4, 3, 2, 1});
    }
}
