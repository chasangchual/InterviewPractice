package practice.interview.sort;

import java.util.Arrays;

public class QuickSort {
    void sort(int[] in, int start, int end) {
        if(end > start) {
            int v = partition(in, start, end);
            sort(in, start, v-1);
            sort(in, v+1, end);
        }
    }
    int partition(int[] arr, int start, int end) {
        int pivot = arr[(end + start) / 2];

        int s = start;
        int e = end;

        while(s < e) {
            while (arr[s] < pivot) s++;
            while (arr[e] > pivot) e--;

            if(s < e) {
                int swap = arr[e];
                arr[e] = arr[s];
                arr[s] = swap;

                s++;
                e--;
            }
        }

        return s;
    }

    public static void main(String[] args) {
        // int[] arr = {4, 9, 3, 10, 93, 1, 23, 2 ,34, 90, 87, 65, 38};
        int[] arr = {5, 4, 3, 2, 1};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr, 0, arr.length-1);

        System.out.println(Arrays.toString(arr));
    }
}
