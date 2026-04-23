package practice.interview.coupang.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAnagrams {
    public List<Integer> solve(String s, String p) {
        List<Integer> found = new ArrayList<>();
        char[] k = p.toCharArray();
        Arrays.sort(k);
        for(int i = 0; i < s.length() - p.length(); i++) {
            char[] x = s.substring(i, i + p.length()).toCharArray();
            Arrays.sort(x);
            if(String.valueOf(k).equals(String.valueOf(x))) {
                found.add(i);
            }
        }
        return found;
    }

    public static void main(String[] args) {
        FindAnagrams findAnagrams = new FindAnagrams();
        System.out.print(findAnagrams.solve("cbaebabacd", "abc"));
    }
}
