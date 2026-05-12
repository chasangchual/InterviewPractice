package interview.ebay;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Given a list of ad click events, return the top K ads by number of clicks.
 * If two ads have the same number of clicks, sort by adId ascending.
 */
public class UserAdClick {
    record AdClick(int addId, long timestamp) {
    }

    public List<Pair<Integer, Integer>> findTop(int k, List<AdClick> clicks) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (AdClick click : clicks) {
            counts.put(click.addId, counts.getOrDefault(click.addId, 0) + 1);
        }

        var sorted = counts.entrySet().stream().sorted((a, b) -> {
                    int compared = b.getValue().compareTo(a.getValue());
                    if (compared != 0) {
                        return compared;
                    }
                    return a.getKey().compareTo(b.getKey());
                })
                .limit(k).map(e -> Pair.of(e.getKey(), e.getValue())).toList();

        return sorted;
    }

    public static void main(String[] args) {
        List<AdClick> clicks = new ArrayList<>();

        Random random = new Random();
        IntStream.range(0, 1000).forEach(
                i -> clicks.add(new AdClick(random.nextInt(50), random.nextInt(200)))
        );

        UserAdClick userAdClick = new UserAdClick();

        List<Pair<Integer, Integer>> found = userAdClick.findTop(5, clicks);
        for (Pair<Integer, Integer> click : found) {
            System.out.println(String.format("ad id: %d, count: %d", click.getLeft(), click.getRight()));
        }
    }
}
