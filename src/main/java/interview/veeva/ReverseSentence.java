package interview.veeva;

public class ReverseSentence {
    public void reverseWord(final String sentence) {
        char[] in = reverse(sentence).toCharArray();
        char[] word = new char[in.length];
        int wordLen = 0 ;
        int len = in.length - 1;

        for(char c : in) {
            if(c == ' ') {
                if(wordLen > 0) {
                    printReverse(word, wordLen);
                    wordLen = 0 ;
                }
                System.out.print(' ');
            } else {
                word[len - (wordLen ++)] = c;
            }
        }

        if(wordLen > 0) {
            printReverse(word, wordLen);
        }
    }

    private void printReverse(final char[] word, final int len) {
        for(int i = word.length - len; i < word.length; i++) {
            System.out.print(word[i]);
        }
    }

    public String reverse(final String input) {
        char[] in = input.toCharArray();
        char[] out = new char[input.length()];
        int index = in.length - 1;
        for(char c : in) {
            out[index --] = c;
        }
        return String.valueOf(out);
    }

    public static void main(String[] args) {
        ReverseSentence reverseSentence = new ReverseSentence();
        System.out.println(reverseSentence.reverse("This is Java"));
        reverseSentence.reverseWord("This is  Java");
    }
}
