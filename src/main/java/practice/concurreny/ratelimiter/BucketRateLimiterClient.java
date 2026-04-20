package practice.concurreny.ratelimiter;

import org.checkerframework.common.value.qual.IntRange;

import java.security.SecureRandom;
import java.util.stream.IntStream;

public class BucketRateLimiterClient {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        BucketRateLimiter bucketRateLimiter = new BucketRateLimiter(15);
        IntStream.range(0, 10000).forEach(  i -> {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(bucketRateLimiter.isGoodToCall()) {
                System.out.println("Good");
            } else {
                System.out.println("Bad");
            }
            }
        );
    }
}
