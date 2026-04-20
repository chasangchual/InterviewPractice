package practice.concurreny.ratelimiter;

import java.time.Duration;
import java.time.LocalDateTime;

public class BucketRateLimiter {
    private int current;
    private final int CAPACITY = 15;
    private final int REFILL = 10;
    private final int COST = 2;
    private final Duration refillPeriod = Duration.ofSeconds(3);
    private LocalDateTime lastFilled;

    public BucketRateLimiter(final int beginWith) {
        this.current = beginWith;
        this.lastFilled = LocalDateTime.now();
    }

    public int getCurrent() {
        return current;
    }

    public boolean isGoodToCall() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current :" + String.valueOf(now) +
                " expected to refill " + String.valueOf(lastFilled.plus(refillPeriod)));

        if(now.isAfter(lastFilled.plus(refillPeriod))) {
            System.out.println("Need to refill " + String.valueOf(this.current));
            this.current = Math.min((this.current + this.REFILL), this.CAPACITY);
            this.lastFilled = LocalDateTime.now();
        }

        if(current >= this.COST ) {
            System.out.println("Good to go with " + String.valueOf(this.current));
            current -= this.COST;
            return true;
        }

        System.out.println("Exhausted " + String.valueOf(this.current));
        return false;
    }
}
