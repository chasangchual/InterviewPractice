package practice.interview.coupang.algorithm;

import java.util.List;
import java.util.Set;

public class DetectSuspiciousUsers {
    public static record Transaction(long timestamp, String userId, int amount) {};
    public Set<String> detectSuspiciousUsers(List<Transaction> transactions, int limit, long windowMillis) {
        return null;
    }
}
