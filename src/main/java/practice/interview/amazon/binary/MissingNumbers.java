package practice.interview.amazon.binary;

public class MissingNumbers {
    public int solution(int in[]) {
        int sum = 0;
        for(int i : in) {
            sum += i;
        }

        int n = in.length +  1;
        return (((n - 1 ) * n) / 2) - sum;
    }

    public static void main(String[] args) {
        int[] in = {3, 0, 1};
        int[] in2 = {9, 6, 4, 2, 3, 5, 7, 0, 1};

        MissingNumbers missingNumbers = new MissingNumbers();

        System.out.println(missingNumbers.solution(in));
        System.out.println(missingNumbers.solution(in2));
    }
}
