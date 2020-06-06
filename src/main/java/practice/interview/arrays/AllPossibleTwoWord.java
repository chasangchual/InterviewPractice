package practice.interview.arrays;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 * Given an array of words, find all possible 2-word pairs, where the words are adjacent. For eg. if input is "My name is Leo", output should be: [[My, name], [name, is], [is, Leo]].

 */

class AllPossibleTwoWord {
    public String solve(final String input) {
        String[] words = input.split(" ");
        List<String> pairedStrs = new ArrayList<>() ;

        for(int i = 0 ; i < words.length - 1; i++) {
            pairedStrs.add(getPairStr(words[i], words[i+1]));
        }
        return Arrays.toString(pairedStrs.toArray());
    }

    public String getPairStr(String left, String right) {
        return "[" + left + ", " + right + "]";
    }

    public static void main(String[] args) {
        String input = "My name is Leo";

        AllPossibleTwoWord solution = new AllPossibleTwoWord();

        if("[My, Name]".equals(solution.getPairStr("My", "Name"))) {
            System.out.println("Success !!");
        } else {
            System.out.println("Fail !!");
        }


        String output =  solution.solve(input);
        if("[[My, name], [name, is], [is, Leo]]".equals(output)) {
            System.out.println("Success !!");
        } else {
            System.out.println("Fail !! output is " + output);
        }
    }
}