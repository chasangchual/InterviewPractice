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

    public List<Pair<Integer, AdClick>> findTop(int k, List<AdClick> clicks ) {
        List<Pair<Integer, AdClick>> topK = new ArrayList<>();
        return topK;
    }

    public static void main(String[] args) {
        List<AdClick> clicks = new ArrayList<>();

        Random random = new Random();
        IntStream.range(0, 1000).forEach(
                i -> clicks.add(new AdClick(random.nextInt(50), random.nextInt(200)))
        );

        UserAdClick userAdClick = new UserAdClick();

        List<Pair<Integer, AdClick>>  found = userAdClick.findTop(5, clicks);
        for(Pair<Integer, AdClick> click : found) {
            System.out.print(String.format("%d, %s", click.getLeft(), click.getRight().toString()));
        }
    }
}
