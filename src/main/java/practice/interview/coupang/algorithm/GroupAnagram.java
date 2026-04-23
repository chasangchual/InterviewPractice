package practice.interview.coupang.algorithm;

import org.apache.commons.lang3.StringUtils;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagram {
    public List<List<String>> solve(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for(String str : strs) {
            char[] key = str.toCharArray();
            Arrays.sort(key);
            if(map.containsKey(String.valueOf(key))) {
                List<String> values = map.get(String.valueOf(key));
                values.add(str);

                map.put(String.valueOf(key), values);
            } else {
                List<String> values = new ArrayList<>();
                values.add(str);
                map.put(String.valueOf(key), values);
            }
        }

        return map.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        GroupAnagram groupAnagram = new GroupAnagram();
        System.out.println(groupAnagram.solve(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }
}
