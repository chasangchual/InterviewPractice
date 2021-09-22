package practice.interview.facebook;

public class StackStabilization {
    public int getMinimumDeflatedDiscCount(int N, int[] R) {
        // Write your code here
        int count = 0;
        for(int i = N - 1; i >= 1; i--) {
            if(R[i] <= 1) return -1;

            if(R[i-1] >= R[i]) {
                deflate(R, i-1);
                count ++;
            }
        }
        return count;
    }

    private void deflate(int[] R, int k) {
        R[k] = R[k+1] - 1;
    }
}
