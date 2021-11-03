package practice.interview.amazon.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * fila all subsets
 *
 * [a, b, c]
 *
 * []
 * [a]
 * [b] [a, b]
 * [c] [a, c] [b, c] [a, b, c]
 */
public class SubSets {
    public List<List<Character>> subsets(char[] in) {
        List<List<Character>> subs = new ArrayList<>();

        // start by adding empty list
        subs.add(new Vector<>());
        for(int i = 0; i < in.length; i++) {
            int size = subs.size();
            for(int k = 0 ; k < size; k++) {
                List added = new ArrayList(subs.get(k));
                added.add(in[i]);
                subs.add(added);
            }
        }
        return subs;
    }

    public static void main(String[] args) {
        SubSets subSets = new SubSets();
        char[] in = "abc".toCharArray();
        System.out.println(subSets.subsets(in));
    }
}
