package practice.interview.sort;

import com.google.common.collect.Lists;

import java.util.Arrays;

public class MergeSort {
    public static void doSort(final int[] items, int left, int right) {
        if(right > left) {
            int m = left + (right - left) / 2;
            doSort(items, left, m);
            doSort(items, m + 1, right);

            merge(items, left, m, right);
        }

        System.out.println(Arrays.toString(items));
    }

    private static void merge(final int[] items, int left, int m, int right) {
        int p1 = left;
        int p2 = m+1;
        int[] sorted = new int[right - left + 1];
        int p = 0;

        while(p1 <= m && p2 <= right) {
            if(items[p1] < items[p2]) {
                sorted[p++] = items[p1++];
            } else {
                sorted[p++] = items[p2++];
            }

            if(p2 > right) {
                while(p1 <= m) {
                    sorted[p++] = items[p1++];
                }
            }

            if(p1 > m) {
                while(p2 <= right) {
                    sorted[p++] = items[p2++];
                }
            }
        }

        for(int i = 0 ; i < sorted.length; i++) {
            items[left + i] = sorted[i];
        }
    }

    public static void main(String[] args) {
        MergeSort.doSort(new int[]{3,7,6,4,2,9,1,8,5}, 0, 8);
    }
}
