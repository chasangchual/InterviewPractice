package practice.interview.coupang;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Real-Time Active Buyer Window
 *
 * You are given order or browsing logs:
 *
 * [timestamp, user_id]
 *
 * For each minute, compute how many unique users were active in the last 10 minutes.
 *
 * Return the time series.
 *
 * Possible follow-ups
 * 	•	Support 1-hour sliding window
 * 	•	Track both page viewers and buyers separately
 * 	•	Optimize for very large log volume
 */
public class RealTimeActiveBuyerWindow {

    record Log(Integer userId, LocalDateTime timestamp) {};
    PriorityQueue<Log> window = new PriorityQueue<>(Comparator.comparing(Log::timestamp));
    Duration windowDuration = Duration.ofMinutes(10);

    public void addLog(Integer userId, LocalDateTime timestamp) {
        window.add(new Log(userId, timestamp));
    }

    private void refreshWindow(LocalDateTime asOfCurrent) {
        while(window.peek() != null &&  window.peek().timestamp.plus(windowDuration).isBefore(asOfCurrent)) {
            window.poll();
        }
    }

    public Set<Integer> findLast10MinActiveUser(LocalDateTime end) {
        refreshWindow(end);
        return findLast10MinActiveUser(new ArrayList<>(window), end);
    }

    private Set<Integer> findLast10MinActiveUser(List<Log> logs, LocalDateTime end) {
        logs.sort(Comparator.comparing(Log::timestamp));
        LocalDateTime start = end.minusMinutes(10);
        LocalDateTime curr = logs.get(0).timestamp;
        List<Log> within10Min = logs.stream().filter(log -> (log.timestamp.isAfter(start) || log.timestamp.isEqual(start)) && (log.timestamp.isEqual(end) || log.timestamp.isBefore(end))).toList();
        return within10Min.stream().map(log -> log.userId).collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        RealTimeActiveBuyerWindow buyerWindow = new RealTimeActiveBuyerWindow();

        LocalDateTime base = LocalDateTime.of(2026, 2, 13, 0, 0, 0);
        SecureRandom random = new SecureRandom();

        IntStream.range(0, 10000).forEach(i-> {
            buyerWindow.addLog(random.nextInt(10), base.minusSeconds(random.nextInt(3) * i));
        });

        buyerWindow.findLast10MinActiveUser(base.minusMinutes(2));
    }
}
