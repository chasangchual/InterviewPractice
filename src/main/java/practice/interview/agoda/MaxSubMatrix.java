package practice.interview.agoda;

import java.util.Arrays;

public class MaxSubMatrix {
    public static int findLagestSubMatrix(int[][] mat) {
        int h = mat.length;
        int w = mat[0].length;

        int[][] s = new int[h][w];

        // fill the first row
        for(int i = 0; i < w; i++) {
            s[0][i] = mat[0][1];
        }

        // fill the first column
        for(int i =0; i < h; i++) {
            s[i][0] = mat[i][0];
        }

        int max = 0;

        for(int i = 1; i < h; i++) {
            for(int k = 1; k < w; k++) {
                if(mat[i][k] == 1) {
                int m = min(s[i-1][k], s[i][k-1], s[i-1][k-1]);
                    s[i][k] = m + 1;
                } else {
                    s[i][k] = 0;
                }
                if(s[i][k] > max) {
                    max = s[i][k];
                }
            }
        }

        for(int i = 1; i < h; i++) {
            System.out.println(Arrays.toString(s[i]));
        }
        return max;
    }

    private static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static void main(String[] args) {
        int[][] mat = {
                {0, 0, 1, 0, 1, 1},
                {0, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 1}
        };

        System.out.println(MaxSubMatrix.findLagestSubMatrix(mat));
    }
}
