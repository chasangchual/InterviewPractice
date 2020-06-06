package practice.interview.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSetCascading {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());

        for(int i = 0 ; i < nums.length; i++) {
            List<List<Integer>> newSubSets = new ArrayList<>();
            Integer num = nums[i];
            subsets.forEach(e -> {
                List<Integer> subset = new ArrayList<>(e);
                subset.add(num);
                newSubSets.add(subset);
            });
            subsets.addAll(newSubSets);
        }
        return subsets;
    }

    public static void main(String[] args) {
        int[] in = {1, 2, 3};
        PowerSetCascading powerSet = new PowerSetCascading();
        System.out.println(powerSet.subsets(in));
    }
}
