package practice.interview.facebook;

public class ScoreboardInference {
    public int getMinProblemCount(int N, int[] S) {
        // Write your code here
        boolean hasOdd = hasOdd(S);
        int max = 0;
        for(int i = 0; i < N; i++) {
            int problems = S[i] != 1 ? minProblems(hasOdd, S[i]) : 1;
            max = problems >= max ? problems : max;
        }
        return max;
    }

    public int minProblems(boolean hasOdd, int s) {
        int count = 0;
        if(hasOdd) {
            count = 2 + ((int)((s - 2) / 2) + (s % 2));
        } else {
            count = (int) (s / 2) + (s % 2);
        }

        return count;
    }

    public boolean hasOdd(int[] S) {
        boolean hasOdd = false;
        for(int i = 0; !hasOdd && i < S.length; i++) {
            hasOdd = isOdd(S[i]);
        }
        return hasOdd;
    }

    public boolean isOdd(int s) {
        return (s % 2) != 0;
    }

    public static void main(String[] args) {
        ScoreboardInference board = new ScoreboardInference();

        System.out.println(board.minProblems(true, 1));
        System.out.println(board.minProblems(false, 1));
    }

}


/**
 * N = 6
 * S = [1, 2, 3, 4, 5, 6]
 * 1 : 1 (1)
 * 2 : 1 + 1 or 2 (2)
 * 3 : 1 + 1 or 1 + 2 (2)
 * 4 : 1 + 1 + 1 + 1 or 2 + 2 or 1 + 1 + 2 or 2 + 1 + 1 (3)
 * 5 : 1 + 2 + 2 or 1 + 1 + 1 + 2 or 1 + 1 + 1 + 1 + 1 (3)
 * 6 : 2 + 2 + 2 or 1 + 1 + 2 + 2 or 1 + 1 + 1 + 1 + 2 or 1 + 1 + 1 + 1 + 1 + + 1  (4)
 *
 * find minimun
 */