package practice.interview.coupang.algorithm;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {
    public int solve(String s) {
        int len = 0;
        Set<Character> sub = new HashSet<>();
        char[] in = s.toCharArray();
        int left = 0;
        for(int right = 0; right < in.length; right++) {
            if(sub.contains(in[right])) {
                for(int i = left ; i < right; i++) {
                    if(in[i] != in[right]) {
                        sub.remove(in[i]);
                    } else {
                        left = i + 1;
                        i = right;
                    }
                }
            } else {
                sub.add(in[right]);
                len = sub.size() > len ? sub.size() : len ;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        System.out.println(lengthOfLongestSubstring.solve("abcabcbb"));;
    }
}
