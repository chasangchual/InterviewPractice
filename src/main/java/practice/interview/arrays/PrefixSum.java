package practice.interview.arrays;

import practice.interview.Pair;

public class PrefixSum {
    public static void getPrefixSum(final Integer[] values, final Pair[] prefixes) {
        Integer sums[] = new Integer[values.length+1];

        sums[0] = 0;
        for(int i = 1; i < values.length+1; i++) {
            sums[i] = sums[i-1] + values[i-1];
        }

        for(Pair prefix : prefixes) {
            System.out.println(sums[prefix.getSecond()] - sums[prefix.getFirst()-1]);
        }
    }

    public static void main(final String args[]) {
        PrefixSum.getPrefixSum(new Integer[]{5, 4, 3, 2, 1}, new Pair[]{new Pair(1, 3), new Pair(2, 4), new Pair(5, 5)});
    }
}
