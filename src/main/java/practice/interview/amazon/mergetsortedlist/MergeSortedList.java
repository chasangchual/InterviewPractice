package practice.interview.amazon.mergetsortedlist;

import java.util.ArrayList;
import java.util.List;

public class MergeSortedList {

    public List<Integer> merge(List<Integer> first, List<Integer>  second) {
        List<Integer> merged = new ArrayList<>();

        Integer i1 = 0, i2 = 0;

        while(i1 < first.size() && i2 < second.size()) {
            if(first.get(i1) > second.get(i2)) {
                merged.add(second.get(i2));
                i2 ++;
            } else if (first.get(i1) < second.get(i2)) {
                merged.add(first.get(i1));
                i1 ++;
            } else {
                merged.add(first.get(i1));
                i1 ++;
                i2 ++;
            }
        }

        if(i1 >= first.size() && i2 < second.size()) {
            for(int i = i2; i < second.size(); i++) {
                merged.add(second.get(i));
            }
        }

        if(i1 < first.size() && i2 >= second.size()) {
            for(int i = i1; i < first.size(); i++) {
                merged.add(first.get(i));
            }
        }

        return merged;
    }

    public static void main(String[] args) {
        MergeSortedList solution = new MergeSortedList();

        List<Integer> first = List.of(1, 4, 5, 6, 9 ,12, 22, 109, 190, 220, 229);
        List<Integer> second = List.of(4, 27, 38, 39, 41, 42, 78, 79, 190, 220, 229);

        List<Integer> merged = solution.merge(first, second);
        System.out.println(merged);

        first = List.of();
        second = List.of();
        merged = solution.merge(first, second);
        System.out.println(merged);

        first = List.of();
        second = List.of(4, 27, 38, 39, 41, 42, 78, 79, 190, 220, 229);
        merged = solution.merge(first, second);
        System.out.println(merged);

        first = List.of(1, 4, 5, 6, 9 ,12, 22, 109, 190, 220, 229);
        second = List.of();
        merged = solution.merge(first, second);
        System.out.println(merged);
    }
}
