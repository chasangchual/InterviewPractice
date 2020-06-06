package practice.interview.twowordpair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of words, find all possible 2-word pairs, where the words are adjacent.
 * For eg. if input is "My name is Leo", output should be: [[My, name], [name, is], [is, Leo]]
 *
 */
public class TwoWordPair {
    public List<List<String>> findPair(final String input) {
        List<List<String>> output = new ArrayList<>();
        String[] words = input.split(" ");

        for(int i = 0 ; i < words.length -1; i++) {
            output.add(Arrays.asList(words[i], words[i+1]));
        }
        return output;
    }

    public static void main(String[] args) {
        TwoWordPair twoWordPair = new TwoWordPair();
        List<List<String>> output = twoWordPair.findPair("My Name is Leg");
        System.out.println("expected 3, actual = " + output.size());
        System.out.println(Arrays.toString(output.toArray()));
    }

}
