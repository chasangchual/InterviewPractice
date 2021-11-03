package practice.interview.amazon.string;

public class UniqueCharacters {
    public Boolean isUnique(String in) {
        boolean[] isDuplicated = new boolean[( ('z' - 'a' + 1) * 2)];
        char[] instr = in.toCharArray();

        for(int i = 0 ; i < instr.length; i++) {
            if((instr[i] >= 'a' && instr[i] <= 'z')) {
                int pos = instr[i] - 'a';
                if(isDuplicated[pos]) {
                    return Boolean.FALSE;
                } else {
                    isDuplicated[pos] = Boolean.TRUE;
                }
            }

            if((instr[i] >= 'A' && instr[i] <= 'Z')) {
                int pos = instr[i] - 'A' + ('z' - 'a' + 1);
                if(isDuplicated[pos]) {
                    return Boolean.FALSE;
                } else {
                    isDuplicated[pos] = Boolean.TRUE;
                }
            }
        }

        return Boolean.TRUE;
    }

    public static void main(String[] args) {
        UniqueCharacters solution = new UniqueCharacters();

        String in = "ATxXbBcCaA";
        System.out.println(in + " is " + (solution.isUnique(in) ? "unique characters" : " is not unique characters"));
    }
}
