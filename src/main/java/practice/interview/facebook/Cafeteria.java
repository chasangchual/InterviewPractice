package practice.interview.facebook;

import com.google.common.collect.Lists;

import java.util.*;

public class Cafeteria {
    public int solve(long N, long K, long M, long[] S) {

        Set<Long> takens = new HashSet<>();

        for(int m = 0; m < M; m++) {
            takens.add(S[m]-1);
        }

        int newSeats = 0;
        long curr = 0;
        long free = K;

        while(curr < N) {
            if (!takens.contains(curr)) {
                if (free >= K) {
                    boolean occupied = false;
                    long occupiedSeat = -1;
                    for (long i = curr+1; !occupied && i <= (curr+K); i++) {
                        occupied = takens.contains(i);
                        occupiedSeat = i;
                    }

                    if (occupied) {
                        curr = occupiedSeat;
                    } else {
                        takens.add(curr);
                        newSeats++;
                    }
                    free = 0;
                } else {
                    free++;
                }
            } else {
                free = 0;
            }

            curr = curr + 1;
        }
        System.out.println(takens.toString());
        return newSeats;
    }

    public static void main(String[] args) {
        Cafeteria cafeteria = new Cafeteria();
        System.out.println(cafeteria.solve(10, 1, 2, new long[]{2, 6}));
        // 0 1 2 3 4 5 6 7 8 9
        //   1       1
        System.out.println(cafeteria.solve(15, 2, 3, new long[]{11, 6, 14}));
        // 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5
        //           1         1     1

        System.out.println(cafeteria.solve(10, 1, 0, new long[]{}));
        // 0 1 2 3 4 5 6 7 8 9
        //

        System.out.println(cafeteria.solve(10, 0, 0, new long[]{}));
    }
}
