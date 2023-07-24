package practice.interview.arrays;

public class LoopUpTables {
    public static int countPowerOfTwo(int[] numbers) {
        int cnt = 0;

        for(int i = 0 ; i < numbers.length; i++) {
            for(int j = 0 ; j <= i; j++) {
                int sum = numbers[i] + numbers[j];

                if(sum > 0) {
                    if((sum & sum - 1) == 0) {
                        System.out.println(numbers[i] + " + " + numbers[j] + " = " + sum);
                    }
                    cnt += (sum & sum - 1) == 0 ? 1 : 0;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(LoopUpTables.countPowerOfTwo(new int[]{1, -1, 2, 3}));
    }
}
