package practice.interview.facebook;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Kaitenzushi {
    public int getMaximumEatenDishCount(int N, int[] D, int K) {
        // Write your code here
        // using queue <- adding dish if the size is less than K. if not, poll() and push
        if(N < 0 || N > 500000) return 0;
        if(K < 0 || K > N) return 0;
        if(D.length < 0 || D.length > 1000000) return 0;

        if(N <= K) return K;

        Queue<Integer> queue = new LinkedList<>();
        int eats = 0;
        for(int i = 0; i < K; i++) {
            eats ++;
            queue.add(D[i]);
        }

        for(int i = K; i < N; i++) {
            if(!queue.contains(D[i])) {
                eats ++;
                queue.poll();
                queue.add(D[i]);
            }
        }

        return eats;
    }

    public static void main(String[] args) {
        Kaitenzushi kaitenzushi = new Kaitenzushi();
        System.out.println(kaitenzushi.getMaximumEatenDishCount(7, new int[]{1, 2, 1, 2, 1, 2, 1}, 2));
    }
}
/*
N = 6
D = [1, 2, 3, 3, 2, 1]
K = 2

N = 7
D = [1, 2, 1, 2, 1, 2, 1]
K = 2
 */