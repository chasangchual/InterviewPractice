package practice.interview.arrays;

import java.util.Arrays;

public class TransformArrayWithSumOfAdjacentValues {
    public static int[] transform(final int[] a) {
        int[] b = new int[a.length];

        for(int i = 0; i < a.length; i++) {
            b[i] = a[i];
            if(i > 0) {
                b[i] += a[i-1];
            }
            if( i < a.length -1) {
                b[i] += a[i+1];
            }
        }

        return b;
    }

    public static void main(String[] args) {
        int[] b = TransformArrayWithSumOfAdjacentValues.transform(new int[]{4, 0, 1, -2 ,3});
        System.out.println(Arrays.toString(b));
    }
}
