package practice.interview.stringmanupulation;

import java.util.Scanner;

public class AlternatingCharacters {

    public Integer solve(final String input) {
        if(input == null || input.length() == 0) {
            return 0;
        }

        int count = 0;
        char c = input.charAt(0);

        for(int i = 1; i < input.length(); i++) {
            if(input.charAt(i) == c)   {
                count ++;
            }
            c = input.charAt(i);
        }

        return count;
    }

    public static void main(String[] args) {
        AlternatingCharacters quiz = new AlternatingCharacters();

        Scanner inScanner = new Scanner(System.in);
        int count = Integer.parseInt(inScanner.nextLine());
        String[] inputs = new String[count];
        for(int i = 0; i < count; i++) {
            inputs[i] = inScanner.nextLine();
        }

        for(String input : inputs) {
            System.out.println(quiz.solve(input)) ;
        }
    }
}
