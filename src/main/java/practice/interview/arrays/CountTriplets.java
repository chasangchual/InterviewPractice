package practice.interview.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountTriplets {

    public static long countTriplets(List<Long> arr, long r) {
        Integer p1 = -1;
        long count = 0;
        //System.out.println(Arrays.toString(arr));

        for(Integer i = 0 ; i < arr.size() - 2; i++) {
            p1 = i;
            List<Integer> indicesP2 = new ArrayList<Integer>();
            List<Integer> indicesP3 = new ArrayList<Integer>();

            if(nextGeometricProgressionsV2(arr, arr.get(p1), p1, r, indicesP2) > 0) {
                for(Integer p2 : indicesP2) {
                    if(nextGeometricProgressionsV2(arr, arr.get(p2), p2, r, indicesP3) > 0) {
                        count = count + indicesP3.size();
                        indicesP3.clear();
                    }
                    System.out.println(String.format("%d, %d", p1, p2));
                }
            }
        }
        return count;
    }

    public static long countTriplets2(List<Long> arr, long r) {
        long count = 0;
        for(int i = 0 ; i < arr.size() - 2; i++) {
            for(int k = i + 1 ; k < arr.size() - 1 && (arr.get(i) ==  arr.get(k) || (arr.get(i) * r) <= arr.get(k)); k++) {
                if((arr.get(i) * r) == arr.get(k)) {
                    for(int m = k + 1 ; m < arr.size() && (arr.get(k) ==  arr.get(m) || (arr.get(k) * r) <= arr.get(m)); m++) {
                        if((arr.get(k) * r) == arr.get(m)) {
                            count ++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static long countTriplets3(List<Long> arr, long r) {
        Map<Long, Long> p1map = new HashMap();
        Map<Long, Long> p2map = new HashMap();
        Map<Long, Long> p3map = new HashMap();

        for(int i = 0 ; i < arr.size() - 2; i++) {
            if(p1map.containsKey(arr.get(i))) {
                p1map.put(arr.get(i), p1map.get(arr.get(i)) + 1);
            } else {
                p1map.put(arr.get(i), 1L);
            }
        }

        for(int i = 1 ; i < arr.size() - 1; i++) {
            if(p2map.containsKey(arr.get(i))) {
                p2map.put(arr.get(i), p2map.get(arr.get(i)) + 1);
            } else {
                p2map.put(arr.get(i), 1L);
            }
        }

        for(int i = 2 ; i < arr.size() ; i++) {
            if(p3map.containsKey(arr.get(i))) {
                p3map.put(arr.get(i), p3map.get(arr.get(i)) + 1);
            } else {
                p3map.put(arr.get(i), 1L);
            }
        }

        Long count = 0L;

        List<Long> list = new ArrayList<>(p1map.keySet());
        // Collections.sort(list);

        for(Long p1 : list) {
            Long p2 = p1 * r;
            Long p3 = p2 * r;
            if(p2map.containsKey(p2) && p3map.containsKey(p3)) {
                count = count + (p2map.get(p2) * p3map.get(p3));
            }
        }
        return count;
    }

    private static int nextGeometricProgressions(List<Long> arr, Long val, int currPos, Long r, List<Integer> indices) {
        if(currPos + 1 < arr.size()) {
            if(val * r == arr.get(currPos+1)) {
                indices.add(currPos+1);
                return nextGeometricProgressions(arr, val, currPos+1, r, indices);
            } else {
                if(val * r < arr.get(currPos+1)) {
                    return indices.size();
                } else {
                    return nextGeometricProgressions(arr, val, currPos+1, r, indices);
                }
            }
        } else {
            return indices.size();
        }
    }

    private static int nextGeometricProgressionsV2(List<Long> arr, Long val, int currPos, Long r, List<Integer> indices) {
        int nextPos = currPos + 1;

        while(nextPos < arr.size() && (val * r) >= arr.get(nextPos)) {
            if(val * r == arr.get(nextPos)) {
                indices.add(nextPos);
            }
            nextPos = nextPos + 1;
        }
        return indices.size();
    }

    private static Integer[] sort(Integer[] arr) {
        List<Integer> sorted = Arrays.asList(arr);
        Collections.sort(sorted);
        return (Integer[]) sorted.toArray();
    }

    public static void main(String[] args) {
        Long count = 0L;

        count = CountTriplets.countTriplets3(new ArrayList<Long>(Arrays.asList(new Long[]{2L, 4L, 8L, 16L})), 2);
        System.out.println(count);

        count = CountTriplets.countTriplets3(new ArrayList<Long>(Arrays.asList(new Long[]{1L, 3L, 9L, 9L, 27L, 81L})), 3);
        System.out.println(count);

        count = CountTriplets.countTriplets3(new ArrayList<Long>(Arrays.asList(new Long[]{1L, 5L, 5L, 25L, 125L})), 5);
        System.out.println(count);

        // expecting 166661666700000
        //           999940001199992
        List<Long> inputs = new ArrayList();
        for(int i = 0 ; i < 100000; i++) {
            inputs.add(1237L);
        }
        count = CountTriplets.countTriplets3(inputs, 1);
        System.out.println(count);

//        CountTriplets.countTriplets(new Long[]{1, 3, 9, 9, 27, 81}, 3);
//        CountTriplets.countTriplets(new Long[]{1, 5, 5, 25, 125}, 5);
    }
}


