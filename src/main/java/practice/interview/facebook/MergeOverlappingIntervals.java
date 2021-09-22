package practice.interview.facebook;

import java.util.ArrayList;
import java.util.List;

public class MergeOverlappingIntervals {
    public List  solve(Pair[] in) {
        List<Pair> out = new ArrayList<>();

        Pair curr = in[0];
        for(int i = 1; i < in.length; i++) {
            if(isOverlapped(curr, in[i])) {
                curr = merge(curr, in[i]);
            } else {
                out.add(curr);
                curr = in[i];
            }
        }

        out.add(curr);
        return out;
    }

    public boolean isOverlapped(Pair one, Pair two) {
        return one.end >= two.start;
    }

    public Pair merge(Pair one, Pair two) {
        return new Pair(one.getStart(), two.getEnd());
    }

    public static void main(String... args) {
        MergeOverlappingIntervals mergeOverlappingIntervals = new MergeOverlappingIntervals();
        System.out.println(mergeOverlappingIntervals.solve(new Pair[]{new Pair(1, 5), new Pair(3, 7), new Pair(4, 6), new Pair(6, 8)}));
        System.out.println(mergeOverlappingIntervals.solve(new Pair[]{new Pair(10, 12), new Pair(12, 15)}));
    }

    static class Pair {
        int start;
        int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", start, end);
        }
    }
}
