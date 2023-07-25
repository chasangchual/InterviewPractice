package practice.interview.agoda;

import java.util.ArrayList;
import java.util.List;

public class LargestSubstringWithoutRepeating {
    public static String find(String str) {
        List<String> subStrings = new ArrayList<String>();

        for(int i = 0; i < str.length(); i++) {
            subStrings.add(find(i, str));
        }

        int max = 0;
        String maxSubstr = "";

        for(String sub : subStrings) {
            if(sub.length() > max) {
                maxSubstr = sub;
                max = sub.length();
            }
        }

        return maxSubstr;
    }

    public static String find(int start, String str) {
        int[] cnt = new int['z' - 'a' + 1];
        int max = 0;
        String maxSub = "";

        for(int i = start; i < str.length(); i++) {
            int k = str.charAt(i)-'a';
            cnt[k] = cnt[k] + 1;
            if(cnt[k] > 1) {
                maxSub = str.substring(start, i);
                break;
            }
        }

        //System.out.println("-----------------------------" + maxSub);
        return maxSub;
    }

    public static void main(String[] args) {
        System.out.println(LargestSubstringWithoutRepeating.find( "abcabcbb"));
        System.out.println(LargestSubstringWithoutRepeating.find( "bbbbbbb"));
        System.out.println(LargestSubstringWithoutRepeating.find( "pwwkew"));
    }
}
