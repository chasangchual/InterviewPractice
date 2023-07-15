package practice.interview.arrays;

import java.util.Arrays;

public class TwoDimensionalArrayTraversal {
    public static int traverse(int[][] field, int[][] figure) {
        int width = field[0].length;
        int fsize = figure.length;

        // width = 5
        // fsize = 3
        // 0, 1, 2 < 3
        // 5 - 3 + 1
        int cnt = 0;
        for(int i = 0 ; i < (width - fsize + 1); i++) {
            int row  = drop(field, figure, i);
            cnt += isFullRow(field, figure, row, i) ? 1 : 0;

        }
        return cnt;
    }

    private static boolean isFullRow(int[][] field, int[][] figure, int row, int col) {
        int width = field[0].length;
        int height = field.length;
        int fsize = figure.length;

        int[][] moved =  new int[height][width];

        for(int[] m : field) {
            System.out.println(Arrays.toString(m));
        }
        System.out.println("---------------------------------");
        for(int[] m : figure) {
            System.out.println(Arrays.toString(m));
        }
        System.out.println("---------------------------------");

        for(int i = 0 ; i < height; i++) {
            moved[i] = field[i].clone();
        }

        for(int i = 0 ; i < fsize; i++) {
            for(int k = 0 ; k < fsize; k++) {
                moved[row+i][col+k] = field[row+i][col+k] == 1 || figure[i][k] == 1 ? 1 : 0;
            }
        }

        for(int[] m : moved) {
            System.out.println(Arrays.toString(m));
        }
        System.out.println("###################################");

        boolean fullRow = false;
        for(int i = row; i < row + fsize && Boolean.FALSE.equals(fullRow); i++) {
            fullRow = true;
            for(int k = 0 ; fullRow && k < width; k++) {
                fullRow = moved[i][k] == 1;
            }
        }

        return fullRow;
    }

    private static int drop(int[][] field, int[][] figure, int position) {
        int height = field.length;
        int fsize = figure.length;

        int r = 0;
        int c = position;
        boolean hit = (r + fsize) > height;

        while(Boolean.FALSE.equals(hit)) {
            for(int i = 0 ; i < fsize && Boolean.FALSE.equals(hit); i++) {
                for(int k = 0; k < fsize && Boolean.FALSE.equals(hit); k++) {
                    hit = figure[i][k] == 1 && field[r+i+1][c+k] == 1;
                }
            }

            if(Boolean.FALSE.equals(hit)) {
                r = r + 1; // move down by 1 row
                hit = (r + fsize) > height;
            }

            if(hit){
                System.out.println("row: " + r  + ", col: " + position);
            }
        }
        return r;
    }

    public static void main(String[] args) {
        int[][] field = new int[][]{
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0},
            {1, 0, 0},
            {1, 1, 0}
        };

        int[][] figure1 = new int[][]{
                {0, 0, 1},
                {0, 1, 1},
                {0, 0, 1}
        };

        System.out.println("===========================================");
        System.out.println(TwoDimensionalArrayTraversal.traverse(field, figure1));
        System.out.println("===========================================");

        field = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 0, 1, 0},
                {1, 0, 1, 0, 1}
        };

        figure1 = new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 0, 1}
        };

        System.out.println("===========================================");
        System.out.println(TwoDimensionalArrayTraversal.traverse(field, figure1));
        System.out.println("===========================================");

        field = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1},
                {1, 1, 0, 1}
        };

        figure1 = new int[][]{
                {1, 1, 0},
                {1, 0, 0},
                {1, 0, 0}
        };

        System.out.println("===========================================");
        System.out.println(TwoDimensionalArrayTraversal.traverse(field, figure1));
        System.out.println("===========================================");
    }
}
