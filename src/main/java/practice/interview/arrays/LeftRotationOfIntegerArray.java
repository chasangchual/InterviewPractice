package practice.interview.arrays;

import java.util.Arrays;

public class LeftRotationOfIntegerArray {
    public static int[] solve(int[] a, int rotation) {
        int[] rotated = new int[a.length];

        for(int i = 0 ; i < rotation; i++) {
            rotated[(a.length - rotation)+i] = a[i];
        }

        for(int i = 0; i < a.length-rotation; i++) {
            rotated[i] = a[i+rotation];
        }

        return rotated;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(LeftRotationOfIntegerArray.solve(a, 3)));
    }
}
