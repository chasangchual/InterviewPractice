package practice.interview;

import java.util.ArrayList;
import java.util.List;

public class SherlockAndAnagrams {
    public static void Solve(char[] str) {
        find_anagram(str);
    }

    private static void find_anagram(char[] str) {
        char[] reversed = reverse(str);
        System.out.println(str);
        System.out.println(reversed);

        int mid = (int) Math.ceil(str.length / 2.0);
        for(int len = 1 ; len <= mid; len++) {
            for(int k = 0 ; k <= mid - len; k ++) {
                char[] find = substract(str, k, len);
                System.out.println(String.valueOf(find));

                find_anagram(find, reversed, k);
/*
                for(int m = 0; m < reversed.length - (k+1); m++) {
                    System.out.println(String.valueOf(substract(str, k, len)) + " : " + String.valueOf(substract(reversed, m, len)));
                }
*/
            }
        }
    }

    private static void find_anagram(char find[], char[] in, int s) {
        for(int i = s ; i < in.length - find.length; i++) {
            char[] sub = substract(in, i, find.length);
            System.out.println(sub);
        }
    }

    private static void find_anagram(char[] from, char[] to) {

    }

    public static char[] reverse(final char[] str) {
        char[] reversed = new char[str.length];
        for(int i = 0; i < str.length; i++) {
            reversed[(str.length - 1) - i] = str[i];
        }
        return reversed;
    }

    /**
     *
     * @param str input string
     * @param start zero based start index. it should be eaual to 0 or greater than 0, and less than the input string length
     * @param len length to subtract
     * @return
     */
    public static char[] substract(final char[] str, int start, int len) {
        if(start < 0 || start >= str.length || len < 0 || len > str.length) {
            throw new IllegalArgumentException();
        }

        if(len == 0) {
            return new char[0];
        }

        char[] substracted = new char[len];
        int end = (start + len) < str.length ? (start + len) : str.length;
        for(int i = start; i < end; i++) {
            substracted[i - start] = str[i];
        }
        return substracted;
    }

    public static boolean equals(char[] from, char[] to) {
        if(from.length != to.length) {
            return false;
        }

        boolean equals = true;
        for(int i = 0 ; equals && i < from.length; i++) {
            equals = from[i] == to[i];
        }

        return equals;
    }

    public static void main(String[] args) {
        SherlockAndAnagrams.Solve(new char[] {'a', 'b', 'c', 'd', 'e'});
        //SherlockAndAnagrams.Solve(new char[] {'a', 'b', 'c', 'b', 'a'});
        // SherlockAndAnagrams.Solve(String.valueOf("abba").toCharArray());

        //System.out.println(SherlockAndAnagrams.substract(new char[] {'a', 'b', 'c', 'e', 'f'}, 0, 3));
        //System.out.println(SherlockAndAnagrams.substract(new char[] {'a', 'b', 'c', 'e', 'f'}, 4, 3));
        //System.out.println(SherlockAndAnagrams.substract(new char[] {'a', 'b', 'c', 'e', 'f'}, 2, 1));
        //System.out.println(SherlockAndAnagrams.substract(new char[] {'a', 'b', 'c', 'e', 'f'}, 2, 0));
    }

    static class Pair<T> {
        T left;
        T right;

        public Pair(T left, T right) {
            this.left = left;
            this.right = right;
        }

        public void setLeft(T left) {
            this.left = left;
        }

        public void setRight(T left) {
            this.right = right;
        }

        public T getLeft() {
            return left;
        }

        public T getRight() {
            return right;
        }

        @Override
        public String toString() {
            return "left: " + left.toString() + "," + "right: " + right.toString();
        }
    }
}
