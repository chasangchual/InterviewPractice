package practice.interview.facebook;


public class CalculatePowerOfNumbers {
    public double power(double a, double b) {

        if (b == 2) return a * a;
        if (b == 1) return a;
        if (b == 0) return 1;

        int m = (int) (b / 2);
        double p = power(a, m);

        p = p * p;
        // if odd
        if((b % 2) == 1) {
            p = p * a;
        }

        return p;
    }


    public static void main(String[] args) {
        CalculatePowerOfNumbers cal = new CalculatePowerOfNumbers();

        System.out.println(cal.power(2, 5));
        System.out.println(cal.power(3, 4));
        System.out.println(cal.power(1.5, 3));
    }
}
