package practice.interview.facebook;

import java.util.ArrayList;
import java.util.List;

public class FindAllSubSets {
    public void solve(char[] in) {
        int n = in.length;
        List<String> values = new ArrayList<>();
        for(int i = 0; i < (1<<n); i++) {
            String w = "";
            for(int k = 0 ; k < n; k++) {
                if ((i & (1 << k) )>= 1) {
                    w = w + in[k];
                }
            }
            if(w.length() > 0) {
                values.add(w);
            }
        }
        System.out.println(values);
    }

    public static void main(String[] args) {
        FindAllSubSets findAllSubSets = new FindAllSubSets();

        char[] in = "12345".toCharArray();
        findAllSubSets.solve(in);
    }
}
