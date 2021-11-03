package practice.interview.amazon.string;

import java.util.*;

public class AllStringPermutation {
    public Set<String> solve(final String in) {
        char[] chars = in.toCharArray();

        Set<String> permutated = new HashSet<>();
        permutated.add(String.valueOf(chars));

        permutate(0, chars, permutated);
        return permutated;
    }

    private void permutate(int i, char[] chars, Set<String> permutated) {
        if(i >= (chars.length - 1)) {
            return;
        }

        for(int k = 0; k < chars.length; k++) {
            char[] swapped = chars.clone();
            char x = swapped[i];
            char y = swapped[k];
            swapped[i] = y;
            swapped[k] = x;
            if(!String.valueOf(swapped).equals(String.valueOf(chars))) {
                permutated.add(String.valueOf(swapped));
            }

            permutate(i+1, swapped, permutated);
        }
    }

    public static void main(String[] args) {
        AllStringPermutation permutation = new AllStringPermutation();
        System.out.println(permutation.solve("ABC"));
    }
}
