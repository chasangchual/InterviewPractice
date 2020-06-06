package practice.interview.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PowerSetBackTracking {
    public List<List<Integer>> subsets(int[] arr) {
        return subsets(0, arr);
    }

    public List<List<Integer>> subsets(int s, int[] arr) {
        List<List<Integer>> subSets = new ArrayList<>();

        if(s == arr.length - 1) {
            subSets.add(new ArrayList<>(Arrays.asList(arr[s])));
        } else {
            List<List<Integer>> newSubSets = subsets(s+1, arr);
            subSets.add(new ArrayList<>(Arrays.asList(arr[s])));

            newSubSets.forEach(e -> {
                subSets.add(e);

                List<Integer> subSet = new ArrayList<>(Arrays.asList(arr[s]));
                subSet.addAll(e);
                subSets.add(subSet);
            });
        }
        return subSets ;
    }

    public static void main(String[] args) {
        PowerSetBackTracking powerSet = new PowerSetBackTracking();
        System.out.println(powerSet.subsets(new int[] {1, 2, 3, 4}));
    }
}
