package interview.ebay;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Given a list of ad click events, return the top K ads by number of clicks.
 * If two ads have the same number of clicks, sort by adId ascending.
 */
public class UserAdClick {
    record AdClick(int addId, long timestamp) {
    }

    public static void main(String[] args) {
        List<AdClick> clicks = new ArrayList<>();

        Random random = new Random();
        IntStream.range(0, 1000).forEach(
                i -> clicks.add(new AdClick(random.nextInt(10), random.nextInt(100)))
        );


    }
}
