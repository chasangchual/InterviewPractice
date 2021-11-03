package practice.interview.amazon.dictionary;

import java.util.List;

public class MissingSpace {
    public String solve(String in, List<String> dictionary) {
        StringBuilder sb = new StringBuilder();
        String word = "";

        for(int i = 0 ; i < in.length(); i++) {
            String f1 = findWord(in.substring(i), 0,  dictionary);
            String f2 = findWord(in.substring(i), f1.length(), dictionary);
            word = f1 + f2;

            i = i + word.length() - 1;
            sb.append(word + " ");
        }

        return sb.toString();
    }

    private String findWord(String in, int end, List<String> dictionary) {
        String word = in.substring(0, end);
        String lookup = in.substring(end);

        for(char c : lookup.toCharArray()) {
            word= word + c;
            if(dictionary.contains(word)) {
                return word.substring(end);
            }
        }

        return "";
    }

    public static void main(String[] args) {
        MissingSpace missingSpace = new MissingSpace();

        String in = "thebluesky";
        List<String> dictionary = List.of("the", "blue", "sky", "green", "is", "grass", "thesis", "i", "party");
        System.out.println(missingSpace.solve(in, dictionary));
        in = "thesisparty";
        System.out.println(missingSpace.solve(in, dictionary));
    }
}
