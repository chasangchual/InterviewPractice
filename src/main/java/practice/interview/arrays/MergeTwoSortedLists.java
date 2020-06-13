package practice.interview.arrays;

import java.util.Arrays;

public class MergeTwoSortedLists {
    public int[] merge(final int[] l1, final int[] l2) {
        int l1_index = 0;
        int l2_index = 0;
        int[] merged = new int[l1.length + l2.length];
        int index = 0;

        while(l1_index < l1.length && l2_index < l2.length) {
            int val1 = l1[l1_index];
            int val2 = l2[l2_index];
            if(val2 > val1) {
                merged[index++] = val1;
                l1_index ++;
            } else if (val1 > val2) {
                merged[index++] = val2;
                l2_index ++;
            } else {
                merged[index++] = val1;
                l1_index ++;
                l2_index ++;
            }
        }
        return Arrays.copyOf(merged, index) ;
    }

    public static void main(String[] args) {
        MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();
        int[] merged = mergeTwoSortedLists.merge(new int[]{1, 2, 4}, new int[]{1, 3, 4});
        System.out.println(Arrays.toString(merged));
    }
}
