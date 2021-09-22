package practice.interview.facebook;

import java.util.Arrays;

public class MoveZerosToLeft {
    public int[] solve(int[] in) {
        int zeroPos = 0;
        int pos = 0;

        while(pos < in.length) {
            if(in[pos] == 0) {
                shift(in, zeroPos, pos);
                in[zeroPos] = 0;
                zeroPos++;
            }
            pos ++;
        }

        return in;
    }

    public void shift(int[] in, int start, int end) {
        if(start == end) {
            return;
        }

        for(int i = end; i > (start); i--) {
            in[i] = in[i-1];
        }
    }

    public static void main(String... args) {
        MoveZerosToLeft moveZerosToLeft = new MoveZerosToLeft();
        int[] in = new int[]{1, 10, 20, 0, 59, 63, 0, 88, 0};

        moveZerosToLeft.shift(in, 0, 0);

        System.out.println(Arrays.toString(in));

        System.out.println(Arrays.toString(moveZerosToLeft.solve(new int[]{1, 10, 20, 0, 59, 63, 0, 88, 0})));
    }
}
