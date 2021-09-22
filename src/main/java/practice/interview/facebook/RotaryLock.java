package practice.interview.facebook;

public class RotaryLock {
    public long getMinCodeEntryTime(int N, int M, int[] C) {
        // Write your code here
        int curr = 1;
        long time = 0;

        for(int i = 0; i < M; i++) {
            time = time + getSec(N, curr, C[i]);
            curr = C[i];
        }

        return time;
    }

    private int getSec(int N, int curr, int next) {
        if(curr == next) return 0;
        int min = curr < next ? curr : next;
        int max = curr < next ? next : curr;

        int dis1 = max - min;
        int dis2 = min + (N - max);
        return dis1 < dis2 ? dis1 : dis2;
    }

    public static void main(String[] args) {

    }
}

/*
N = 3
M = 3
C = [1, 2, 3]

N = 10
M = 4
C = [9, 4, 4, 8]
 */