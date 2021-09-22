package practice.interview.facebook;

import java.util.HashSet;
import java.util.Set;

public class FindAllSubSets2 {
    Set<String> sets = new HashSet<>();

    public Set<String> getSets() {
        return this.sets;
    }

    public void solve(int in) {
        char[] a = String.valueOf(in).toCharArray();

        for(int i = 0; i < a.length; i++) {
            sets.add(String.valueOf(a[i]));
        }

        for(int size = 1; size <= a.length; size++) {
            for(int i = 0; i < a.length; i++) {
                if(size < (a.length - i)) {
                    char[] sub = substract(a, i, size);
                    // System.out.println(sub);
                    makeCombination(a, i, i + (size - 1), sub);
                }
            }
        }
    }

    public void makeCombination(char[] a, int start, int end, char[] sub) {
        for(int i = 0 ; i < start; i++) {
            String out = String.valueOf(a[i]);
            out = out + String.valueOf(sub);
            if(!sets.contains(out)) {
                sets.add(out);
            }
        }

        for(int i = end + 1 ; i < a.length; i++) {
            String out = String.valueOf(sub);
            out = out + String.valueOf(a[i]);
            if(!sets.contains(out)) {
                sets.add(out);
            }
        }
    }

    public char[] substract(char[] in, int start, int len) {
        char[] sub = new char[len];
        for(int i = 0; i < len; i++) {
            sub[i] = in[start+i];
        }
        return sub;
    }

    public static void main(String[] args) {
        int in = 1234567;
        FindAllSubSets2 findAllSubSets = new FindAllSubSets2();
        findAllSubSets.solve(in);
        System.out.println(findAllSubSets.getSets());
    }
}
