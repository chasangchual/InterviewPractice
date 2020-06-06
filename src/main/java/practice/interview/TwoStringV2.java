package practice.interview;

import java.util.Arrays;

public class TwoStringV2 {

    private static final int A_DISTANCE = 'a' - 'A';

    public static void solve(final String str1, final String str2) {
        char[] l = str1.length() > str2.length() ? str1.toCharArray() : str2.toCharArray(); // longer
        char[] s = str1.length() > str2.length() ? str2.toCharArray() : str1.toCharArray(); // shorter

        int pos = 0;
        boolean found = false;

        // System.out.println(String.format("%s   %s", Arrays.toString(l), Arrays.toString(s)));

        for(int i = 0 ; i < l.length && !found; i++) {
            if((pos = exists(s, pos, l[i])) != -1) {

                System.out.println("YES");
                int len = findSameString(l, i, s, pos);
                System.out.println(Arrays.copyOfRange(l, i, i + len));
                return;
            } else {
                pos = 0;
            }
        }
        System.out.println("NO");
    }

    public static int exists(final char[] str, final int start, final char c) {
        int pos = -1;

        for(int i = start; i < str.length && pos == -1; i++) {
            // System.out.println(String.format("%c %c", toUpper(str[i]), toUpper(c)));
            if(toUpper(str[i]) == toUpper(c)) {
                pos = i;
            }
        }

        return pos;
    }

    public static int findSameString(final char[] s1, final int p1, final char[] s2, final int p2) {
        int len = 0;
        boolean matched = true;

        /////// This is tricky part
        for(int i = p1; i < s1.length && p2+len < s2.length && matched; i++) {
            if(s1[i] == s2[p2+len]) {
                len ++;
            } else {
                matched = false;
            }
        }

        return len;
    }

    // Character.toUpperCase(c);
    public static char toUpper(final char c) {
        if(c >= 'a' || c <= 'z') {
            /////// This is tricky part
            int t = c - A_DISTANCE;
            return (char)t;
        }
        return c;
    }
    public static void main(String[] args) {
        TwoStringV2.solve("hello", "world");
        TwoStringV2.solve("hi", "world");
        TwoStringV2.solve("hellobc", "kallolllm");
    }
}
