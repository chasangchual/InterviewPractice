package practice.interview.arrays;

import java.util.Arrays;

public class MinimunSwaps {
    public static void solve(int[] arr) {
        Pair swapPair = new Pair();
        System.out.println(Arrays.toString(arr));
        int count =0;
        while(findSwap(arr, swapPair)) {
            int temp = arr[swapPair.left];
            arr[swapPair.getLeft()] = arr[swapPair.getRight()];
            arr[swapPair.getRight()] = temp;
            System.out.println(Arrays.toString(arr));
            count ++;
        }
        System.out.println(count);
    }

    private static boolean findSwap(final int[] arr, Pair swapPair) {
        boolean found = false;
        int from = -1;
        int to = -1 ;

        for(int i = 0 ; i < arr.length && !found; i++) {
            if(arr[i] != i+1) {
                swapPair.setLeft(i);
                swapPair.setRight(arr[i]-1);
                found = true;
            }
        }

        return found;
    }

    public static void main(String[] args) {
        MinimunSwaps.solve(new int[]{4, 3, 1 , 2});
        MinimunSwaps.solve(new int[]{1, 3, 5 , 2, 4, 6 ,7});
    }

    static class Pair {
        int left = -1;
        int right = -1;

        public Pair() {

        }

        public Pair(final int left, final int right) {
            this.left = left;
            this.right = right;
        }

        public void setLeft(final int left) {
            this.left = left;
        }

        public void setRight(final int right) {
            this.right = right;
        }

        public int getLeft() {
            return this.left;
        }

        public int getRight() {
            return this.right;
        }
    }
}
