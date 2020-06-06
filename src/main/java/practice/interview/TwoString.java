package practice.interview;

public class TwoString {

    private static final int A_DISTANCE = 'a' - 'A';

    public static void solve(final String str1, final String str2) {
        char[] l = str1.length() > str2.length() ? str1.toCharArray() : str2.toCharArray(); // longer
        char[] s = str1.length() > str2.length() ? str2.toCharArray() : str1.toCharArray(); // shorter

        int pos = 0;
        boolean found = false;

        for(int i = 0 ; i < l.length && !found; i++) {
            if((pos = exists(s, pos, l[i])) != -1) {
                System.out.println("YES");
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
            if(toUpper(str[i]) == toUpper(c)) {
                pos = i;
            }
        }

        return pos;
    }

    // Character.toUpperCase(c);
    public static char toUpper(final char c) {
        if(c >= 'a' || c <= 'z') {
            int t = c - A_DISTANCE;
            return (char)t;
        }
        return c;
    }
    public static void main(String[] args) {
        TwoString.solve("hello", "world");
        TwoString.solve("hi", "world");
    }
}
