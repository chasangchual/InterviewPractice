package interview.ebay;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Implement a rate limiter for API requests.
 * Each user can make at most N requests in a rolling window of W seconds.
 */
public class SlidingWindowRateLimiter {
    Map<Integer, Deque<Long>> userWindow = new HashMap<>();
    private final int WINDOW_SEC = 1;
    private final int MAX_CALLS_IN_WINDOWS = 3;

    public boolean isAllowed(Integer userId) {
        Long current = System.currentTimeMillis();
        Long from = current - (WINDOW_SEC * 1000); // time window

        Deque<Long> window = userWindow.computeIfAbsent(userId, k -> new ArrayDeque<Long>());

        if(window.isEmpty() || window.size() < MAX_CALLS_IN_WINDOWS) {
            window.addLast(current);
            userWindow.put(userId, window);
            return true;
        }

        while(!window.isEmpty()  && window.peekFirst() <= from) {
            window.pollFirst();
        }

        if(window.size() < MAX_CALLS_IN_WINDOWS ) {
            window.addLast(current);
            userWindow.put(userId, window);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter();

        IntStream.range(0, 10000).forEach(i ->
        {
            try {
                LocalDateTime now = LocalDateTime.now();

                Integer userId = random.nextInt(10);
                Boolean allowed = limiter.isAllowed(userId);
                if(!allowed) {
                    System.out.println(String.format("Late limiter blocked: user %d, current %s", userId, now.toString()));
                } else {
                    // System.out.println(String.format("Late limiter allowed: user %d, current %s", userId, now.toString()));
                }
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
