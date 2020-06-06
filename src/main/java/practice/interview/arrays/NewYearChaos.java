package practice.interview.arrays;

import java.util.Arrays;
import java.util.stream.IntStream;

public class NewYearChaos {
    private static final int MAX_TRIES = 2;

    public static void solve(int[] finalState) {
        int[] queue = Arrays.copyOf(finalState, finalState.length);
        int[] tries = new int[finalState.length];

        boolean end = false;
        boolean isOverMaxTries = false;

        Pair<Integer, Integer> bibery = new Pair<>();
        int moves = 0;

        // System.out.println("Before: " + Arrays.toString(finalState)) ;
        while(!end) {
            boolean found = getBribery(queue, bibery);
            if(found) {
                if(tries[queue[bibery.getLeft()]-1] <= MAX_TRIES && tries[queue[bibery.getRight()]-1] <= MAX_TRIES) {

                    // System.out.println("left : " + String.valueOf(queue[bibery.getLeft()]) +
                    //        " right : " + String.valueOf(queue[bibery.getRight()])) ;

                    tries[queue[bibery.getLeft()]-1] = tries[queue[bibery.getLeft()]-1] + 1;
                    tries[queue[bibery.getRight()]-1] = tries[queue[bibery.getRight()]-1] + 1;
                    moveBack(queue, bibery);

                    // System.out.println("tries: " + Arrays.toString(tries)) ;
                    moves++;
                } else {
                    isOverMaxTries = true;
                    end = true;
                }
            } else {
                end = true ;
            }
        }

        if(isOverMaxTries) {
            System.out.println("Too chaotic");
        } else {
            System.out.println(moves);
        }
    }

    private static void moveBack(int[] a, Pair<Integer, Integer> bibery) {
        if ((bibery.getLeft() >= 0 && bibery.getLeft() < a.length) &&
                (bibery.getRight() >= 0 && bibery.getRight() < a.length)) {

            // System.out.println(String.format("switch %dth and %dth", bibery.getLeft()+1, bibery.getRight()+1));

            Integer temp = a[bibery.getLeft()];
            a[bibery.getLeft()] = a[bibery.getRight()];
            a[bibery.getRight()] = temp;
            // System.out.println("After: " + Arrays.toString(a) + "\n") ;
        } else {
            throw new IllegalArgumentException("out of range");
        }
    }

    private static boolean getBribery(int[] a, Pair<Integer, Integer> bibery) {
        boolean found = false;
        int briber = -1, bribee = -1;
        for(int i = 0 ; i < a.length && !found; i++) {
            if(a[i] != i+1) { // detect bribe
                briber = i;
                bribee = a[i]-1;
                found = true;
            }
        }

        bibery.setLeft(briber);
        bibery.setRight(bribee);

        return found;
    }

    public static void main(String[] args) {
        NewYearChaos.solve(new int[] {5, 1, 2, 3, 7, 8, 6, 4});
        NewYearChaos.solve(new int[] {1, 2, 5, 3, 7, 8, 6, 4});

        NewYearChaos.solve(new int[] {2, 1, 5, 3, 4});
        NewYearChaos.solve(new int[] {2, 5, 1, 3, 4});

        NewYearChaos.solve(new int[] {1, 2, 5, 3, 4, 7, 8, 6});
/*

        2
        8
        5 1 2 3 7 8 6 4
        8
        1 2 5 3 7 8 6 4


        Too chaotic
        7


        2
        5
        2 1 5 3 4
        5
        2 5 1 3 4

        3
        Too chaotic


        1
        8
        1 2 5 3 4 7 8 6

        4
*/

    }

    static class Pair<L, R> {
        L left;
        R right;

        public Pair() {

        }

        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public L getLeft() {
            return this.left;
        }

        public R getRight() {
            return this.right;
        }

        public void setLeft(L left) {
            this.left = left;
        }

        public void setRight(R right) {
            this.right = right;
        }
    }
}
