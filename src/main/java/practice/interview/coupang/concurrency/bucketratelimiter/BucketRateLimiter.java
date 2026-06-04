package practice.interview.coupang.concurrency.bucketratelimiter;

import java.time.Duration;

public class BucketRateLimiter {
    int bucketSize ;

    private static int CONSUME_PER_BUCKET = 1;
    private static int REFILLP_SIZE = 6;
    private static Duration refileDuration = Duration.ofSeconds(1);
    private long lastTimeRefill ;

    public BucketRateLimiter(int bucketSize) {
        this.bucketSize = bucketSize;
        this.lastTimeRefill = System.currentTimeMillis();
    }

    private void refillBucket() {
        long now = System.currentTimeMillis();

        if(lastTimeRefill + refileDuration.toMillis() > now) {
            this.bucketSize += REFILLP_SIZE;
            this.lastTimeRefill = now;
        }
    }

    public boolean isGood() {
        refillBucket();

        if(this.bucketSize >= CONSUME_PER_BUCKET) {
            this.bucketSize -= CONSUME_PER_BUCKET;
            return true;
        }

        return false;
    }
}
