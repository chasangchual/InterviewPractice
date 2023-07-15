package practice.interview.arrays;

public class VowelConsonantMatchingPatterns {
    private static final char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u'};

    public static int match(char[] str, int[] pattern) {
        // ensure the input string contains only low cased alphabet
        int cnt = 0;
        for(int i = 0 ; i < (str.length - pattern.length + 1); i++) {
            cnt += matched(str, i, pattern) ? 1 : 0;
        }
        return cnt;
    }

    private static boolean matched(char[] str, int l, int[] pattern) {
        boolean isMatched = true;

        for(int i = 0; i < pattern.length && Boolean.TRUE.equals(isMatched); i++) {
            if(pattern[i] == 0) {
                boolean isVowel = false;
                int k = 0;
                while(k < vowels.length && Boolean.FALSE.equals(isVowel)) {
                    isVowel = str[i+l] == vowels[k++];
                }
                isMatched = isVowel;
            } else {
                boolean isConsonant = false;
                int k = 0;
                while(k < vowels.length && Boolean.FALSE.equals(isConsonant)) {
                    isConsonant = str[i+l] != vowels[k++];
                }
                isMatched = isConsonant;
            }
        }
        return isMatched;
    }

    public static void main(String[] args) {
        System.out.println(VowelConsonantMatchingPatterns.match("amazing".toCharArray(), new int[]{0, 1, 0}));
        System.out.println(VowelConsonantMatchingPatterns.match("codesignal".toCharArray(), new int[]{1, 0, 0}));
    }
}
