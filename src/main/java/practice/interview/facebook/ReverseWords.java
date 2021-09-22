package practice.interview.facebook;

public class ReverseWords {
    public static void main(String[] args) {
        char[] in = "Hello World".toCharArray();

        String word= "";
        for(int i = 0; i < in.length; i++) {

            if(in[i] != ' ') {
                word= word + in[i];
            } else {
                char[] w = word.toCharArray();
                for(int k = w.length - 1; k >= 0; k--) {
                    System.out.print(w[k]);
                }
                System.out.print(in[i]);
                word = "";
            }
        }
        if(word.length() > 0) {
            char[] w = word.toCharArray();
            for(int k = w.length - 1; k >= 0; k--) {
                System.out.print(w[k]);
            }
        }
    }
}
