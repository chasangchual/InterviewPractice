package practice.interview.facebook;

public class UniformIntegers {
    public int getUniformIntegerCountInInterval(long A, long B) {
        // Write your code here

        if(A == B) return 1;
        if (B < 10) return (int)(B - A + 1);

        int digits1 = digitCount(A);
        int digits2 = digitCount(B);

        int nextUniformNum1 = nextUniformNum(A);
        int nextUniformNum2 = nextUniformNum(B);

        if(digits1 == digits2) {
            return (nextUniformNum2 - nextUniformNum1) ;
        } else {
            int b = (int)( B / (long) (Math.pow(10, digits2-1)));

            int count = (10 - nextUniformNum1) + (10 * (digits2 - digits1 - 1)) +  (nextUniformNum2 > b ? b : (b-1));
            return count;
        }
    }


    private int nextUniformNum(long V) {
        if(V < 10) return (int) V;

        int digits = digitCount(V);
        int num1 = (int)( V / (long) (Math.pow(10, digits-1)));
        long v = V - (num1 * (long) (Math.pow(10, digits-1)));

        int nextUniformNum = nextUniformNum(v, num1, digits -1);

        return nextUniformNum;
    }

    private int nextUniformNum(long v, int num1, int digits) {
        int num2 = (int) (v / (long) (Math.pow(10, digits-1)));
        return num1 >= num2 ? num1 : num2;
    }

    private boolean isUniform(long V) {
        if(V < 10) {
            return true;
        }

        boolean isUniform = false;

        long num = V % 10;
        long v = V / 10;

        while(!isUniform && v > 0) {
            isUniform = (v % 10) == num;
            v = v / 10;
        }

        return isUniform;
    }

    public int digitCount(long V) {
        return V == 0 ? 1 : (int) Math.log10(V) + 1;
    }

    public static void main(String[] args) {
        UniformIntegers question = new UniformIntegers();
//        System.out.println(question.isUniform(12345));
//        System.out.println(question.isUniform(1));
//        System.out.println(question.isUniform(101));
//        System.out.println(question.isUniform(111));
//        System.out.println(question.nextUniformNum(896, 7, 3));
//         System.out.println(question.nextUniformNum(75));
        System.out.println(question.getUniformIntegerCountInInterval(100, 200));
    }
}
