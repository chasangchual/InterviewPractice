package practice.interview.map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class ItemCounts {
    public Map<String, Integer> getCount(List<String> items) {
        Map<String, Integer> counts = new HashMap<>();
        for(String item : items) {
            counts.put(item, counts.getOrDefault(item, 1) + 1);
        }
        return counts;
    }

    public static void main(String[] args) {
        String[] fruites = new String[]{"Blueberry", "Blackberry" ,"Raspberry", "Cranberry", "Gooseberry", "Lemon", "Lime", "Grapefruit", "Mandarin", "Tangerine", "Pitaya", "Durian", "Kiwi", "Passion Fruit", "Rambutan"} ;

        List<String> items = new ArrayList<>();

        Random random = new Random();
        IntStream.range(0, 1000).forEach(i -> items.add(fruites[random.nextInt(fruites.length)]));

        ItemCounts itemCounts = new ItemCounts();
        Map<String, Integer> wordCount = itemCounts.getCount(items);
        wordCount.entrySet().stream().forEach(e ->
                System.out.println(String.format("%s %d", e.getKey(), e.getValue())));
    }
}
