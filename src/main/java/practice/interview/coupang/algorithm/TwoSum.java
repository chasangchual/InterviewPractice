package practice.interview.coupang.algorithm;

import jdk.jfr.Frequency;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {
    public int[] solve(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i =0 ; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for(int i =0 ; i < nums.length; i++) {
            int curr = nums[i];
            int required = target - curr;
            if(map.containsKey(required) && map.get(required) != i) {
                return new int[]{i, map.get(required)};
            }
        }
        return null;
    }
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        System.out.println(Arrays.toString(twoSum.solve(new int[]{2, 7, 11, 15}, 9)));
    }

}
