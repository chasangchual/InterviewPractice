package practice.interview.arrays;

public class SwapWithoutTemp {
    public static void doSwap(int x, int y) {
        x = x + y;
        y = x - y;
        x = x - y;
    }

    public static void main(String[] args) {
        int x  = 10;
        int y =  7;

        System.out.println(x + " " + y);

        x = x + y;
        y = x - y;
        x = x - y;

        System.out.println(x + " " + y);
    }
}
