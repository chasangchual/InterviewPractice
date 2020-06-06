package practice.interview.findnwordpairs;

import java.util.ArrayList;
import java.util.List;

public class FindNWordPairs {
    public List<List<String>> findNWordPairs(final String[] input, final int n) {
        List<List<String>> nWordPairs = new ArrayList<>();
        for(int i = 0 ; i < (input.length - n + 1); i++) {
            List<String> words = new ArrayList<>();

            for(int k = 0; k < n; k++) {
                words.add(input[i+k]);
            }
            nWordPairs.add(words);
        }
        return nWordPairs;
    }

    public static void main(String[] args) {
        FindNWordPairs findNWordPairs = new FindNWordPairs();

        List<List<String>> output = findNWordPairs.findNWordPairs("My name is Leo".split(" "), 2);
        System.out.println("expected 3, actual = " + output.size());
        System.out.println(output.toString());

        output = findNWordPairs.findNWordPairs("My name is Leo".split(" "), 3);
        System.out.println("expected 2, actual = " + output.size());
        System.out.println(output.toString());
    }

}
