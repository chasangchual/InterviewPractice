package practice.interview.sort;

import java.util.Arrays;

public class MaxToys {
    public static int maximumToys(int[] prices, int k) {
        Arrays.sort(prices);
        int priceSum = 0;
        int count = 0;
        for(int i = 0 ; i < prices.length && priceSum <= k; i++) {
            if(priceSum + prices[count] <= k) {
                priceSum += prices[count];
                count ++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int count = 0;
        count = MaxToys.maximumToys(new int[]{1, 12, 5, 111,200, 1000, 10}, 50);
        System.out.println(String.format("count = %d", count));

        count = MaxToys.maximumToys(new int[]{1, 2, 3, 4}, 7);
        System.out.println(String.format("count = %d", count));

        count = MaxToys.maximumToys(new int[]{3, 7, 2, 9, 4}, 15);
        System.out.println(String.format("count = %d", count));

        /*
        4 7
        1 2 3 4
        3

        5 15
        3 7 2 9 4
        3
         */
    }
}
