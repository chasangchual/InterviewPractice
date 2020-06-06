package practice.interview.sort;

import java.util.Arrays;

public class FraudulentActivityNotifications {
    public static int solve(int[] expenditure, int d) {
        int day = 0;
        int notiCount = 0;

        while(day < expenditure.length) {
            if(day >= d && expenditure[day] >= (2 * getMedian(expenditure, day-1, d))) {
                // System.out.println("expenditure: " +expenditure[day]);
                notiCount ++;
            }
            day ++;
        }
        return notiCount;
    }

    private static double getMedian(int[] transactions, int end, int trailingDays) {
        boolean isEven = trailingDays % 2 == 0;
        int begin =  end - trailingDays + 1;
        int[] days = Arrays.copyOfRange(transactions, begin, end+1);
        Arrays.sort(days);
        int mid = Math.floorDiv(days.length, 2);

        double median = isEven ? (days[mid-1] + days[mid]) / 2.0 : days[mid];

        // System.out.println( begin + " - "  + end + "     "  + Arrays.toString(days) + "   median: " + median + " " + (isEven ? days[mid-1] + " : " + days[mid]  : ""));

        return median;
    }

    public static void main(String[] args) {
        System.out.println(FraudulentActivityNotifications.solve(new int[]{2, 3, 4, 2, 3, 6, 8, 4, 5}, 5));
        System.out.println(FraudulentActivityNotifications.solve(new int[]{1, 2, 3, 4, 4}, 4));
        System.out.println(FraudulentActivityNotifications.solve(new int[]{10, 20, 30, 40, 50}, 3));
        /**
         5 4
         1 2 3 4 4
         expected 0

         5 3
         10 20 30 40 50

         expected
         1
         */
    }
}
