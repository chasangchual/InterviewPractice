package practice.interview.arrays;

import practice.interview.Pair;

public class PreFixSum2D {
    public static void getFixSum(Integer size, Pair start, Pair end) {
        Integer matrix[][] = new Integer[size][size];

        for(int i = 0; i < size; i++) {
            for(int k = 0; k < size; k++) {
                matrix[i][k] = i + k + 1;
            }
        }

        Integer sumMatrix[][] = new Integer[size+1][size+1];
        Integer horizontalMatrix[][] = new Integer[size+1][size+1];

        for(int i = 0; i < size + 1; i++) {
            sumMatrix[i][0] = 0;
            sumMatrix[0][i] = 0;

            horizontalMatrix[i][0] = 0;
            horizontalMatrix[0][i] = 0;
        }

        for(int i = 1 ; i < size + 1; i++) {
            for(int k = 1; k < size + 1; k++) {
                horizontalMatrix[i][k] = horizontalMatrix[i][k-1] + matrix[i-1][k-1] ;
                sumMatrix[i][k] = horizontalMatrix[i][k-1] + sumMatrix[i-1][k] + matrix[i-1][k-1] ;
            }
        }

        System.out.println(sumMatrix[end.getFirst()][end.getSecond()] - sumMatrix[start.getFirst()][end.getSecond()]- sumMatrix[end.getFirst()][start.getSecond()] + sumMatrix[start.getFirst()][start.getSecond()]);
    }

    public static void main(String[] args) {
        PreFixSum2D.getFixSum(4, new Pair(2,2), new Pair(3,4));
        PreFixSum2D.getFixSum(4, new Pair(3,4), new Pair(3,4));
        PreFixSum2D.getFixSum(4, new Pair(1,1), new Pair(4,4));
    }
}
