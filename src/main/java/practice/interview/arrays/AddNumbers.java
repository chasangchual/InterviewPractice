package practice.interview.arrays;

public class AddNumbers {
    public static Long add(final int len, final String nums) {
        if(len < 1 || len > 100) {
            throw new IllegalArgumentException();
        }

        if(len != nums.length()) {
            throw new IllegalArgumentException();
        }

        Long sum = Long.valueOf(0);

        for(char num : nums.toCharArray()) {
            // sum += Long.valueOf(num - '0');
            sum += Long.parseLong(String.valueOf(num));
        }

        return sum;
    }

    public static void main(String args[]) {
        System.out.println(AddNumbers.add(1, "1"));
        System.out.println(AddNumbers.add(5, "54321"));
        System.out.println(AddNumbers.add(25, "7000000000000000000000000"));
        System.out.println(AddNumbers.add(11, "10987654321"));
    }
}
