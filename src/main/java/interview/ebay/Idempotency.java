package interview.ebay;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a stream of ad impression events, count unique impressions.
 * Each event has eventId, adId, userId, and timestamp.
 * Duplicate eventIds should be ignored.
 * Return impression count per ad.
 */
public class Idempotency {
    record Event(Integer eventId, Integer adId, Integer userId, Integer timestamp) {};

    public Map<Integer, Integer> getImpression(List<Event> events) {
        Map<Integer, Integer> eventCounts = new HashMap<>();
        events.stream().forEach(e ->
                        eventCounts.put(e.eventId, eventCounts.getOrDefault(e.eventId, 0) + 1)
                );

        List<Event> distinctEvents = events.stream().collect(Collectors.toMap(
                Event::eventId, e -> e, (existing, replacement) -> existing
        )).values().stream().toList();

        // counter per add
        Map<Integer, Integer> adImpression = distinctEvents.stream().collect(Collectors.toMap(Event::adId, e -> 0, (existing, replacement) -> existing + 1));

        Integer total = adImpression.values().stream().reduce(0, (a,b) -> a + b);
        return adImpression;
    }

    public static void main(String[] args) {
        List<Event> events = new ArrayList<>();

        SecureRandom random = new SecureRandom();

        IntStream.range(0, 10000).forEach(i -> {
            events.add(new Event(random.nextInt(5000), random.nextInt(100), random.nextInt(50), random.nextInt(600)));
        });

        Idempotency idempotency = new Idempotency();
        idempotency.getImpression(events);
    }
}
