package practice.interview.amazon.string;

public class Anagram {
    public Boolean isAnagram(String in1, String in2) {
        if(in1.length() != in2.length()) {
            return false;
        }

        int[] count = new int[256];

        char[] instr1 = in1.toCharArray();
        char[] instr2 = in2.toCharArray();

        for(int i = 0; i < in1.length(); i++) {
            count[instr1[i]] ++;
            count[instr2[i]] --;
        }

        for(int i = 0; i < count.length; i++) {
            if(count[i] != 0) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public static void main(String[] params) {
        Anagram anagram = new Anagram();

        String in1 = "LISTEN";
        String in2 = "SILENT";

        System.out.println(in1 + " and " + in2 + " is" + (anagram.isAnagram(in1, in2) ? " " : " not ") + "anagram.");

        in1 = "TRIANGLE";
        in2 = "INTEGRAL";

        System.out.println(in1 + " and " + in2 + " is" + (anagram.isAnagram(in1, in2) ? " " : " not ") + "anagram.");
    }
}
