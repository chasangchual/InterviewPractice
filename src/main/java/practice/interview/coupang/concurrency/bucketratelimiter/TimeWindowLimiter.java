package practice.interview.coupang.concurrency.bucketratelimiter;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.*;
import java.util.stream.IntStream;

public class TimeWindowLimiter {
    Queue<Long> window = new PriorityQueue<>();
    final int capacity ;
    final Duration duration ;
    public TimeWindowLimiter(int capacity, int windowSizeMilliSec) {
        this.capacity = capacity;
        duration = Duration.ofMillis(windowSizeMilliSec);
    }

    public Boolean isValidState() {
        refreshWindow();
        if(window.size() < capacity) {
            window.add(System.currentTimeMillis());
            return true;
        }
        return false;
    }

    private void refreshWindow() {
        long now = System.currentTimeMillis();

        while(window.peek() != null && window.peek() < now - duration.toMillis()) {
            window.poll();
        }
    }
    public int getSize() {
        return window.size();
    }

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();

        TimeWindowLimiter limiter = new TimeWindowLimiter(10, 1000);
        IntStream.range(0, 100).forEach(i -> {
                try {
                    System.out.println(String.format("current window size: %d", limiter.getSize()));
                    if(limiter.isValidState()) {
                        System.out.println("ok");
                    } else {
                        System.out.println("wait");
                    }
                    int sleep = random.nextInt(250);
                    Thread.sleep(sleep);
                    System.out.println(String.format("wait for %d milliseconds", sleep));
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
        });
    }
}
