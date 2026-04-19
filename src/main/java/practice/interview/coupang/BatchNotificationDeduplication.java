package practice.interview.coupang;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Batch Notification Deduplication
 *
 * You are given notification events:
 *
 * [timestamp, user_id, channel, message_type, order_id]
 *
 * A user should not receive the same notification more than once for the same order and message type.
 *
 * Return only the deduplicated notifications to send.
 *
 * Possible follow-ups
 * 	•	Prioritize SMS over email for urgent messages
 * 	•	Respect user notification preferences
 * 	•	Limit to at most 3 notifications per user per hour
 */
public class BatchNotificationDeduplication {
    public record NotificationEvent(LocalDateTime timestamp, long userId, int orderId, int channel, String messageType) {};

    public List<NotificationEvent> filterDuplicated(List<NotificationEvent> sentNotifications) {
        List<NotificationEvent> dups = new ArrayList<>();
        Map<Integer, Set<String>> processed = new HashMap<>();

        sentNotifications.forEach(n -> {
            processed.putIfAbsent(n.orderId, new HashSet<>());
            var types = processed.get(n.orderId);
            if(!types.add(n.messageType)) {
                dups.add(n);
            }
        });
        return dups;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        List<NotificationEvent> sents = new ArrayList<>();
        sents.add(new NotificationEvent(LocalDate.parse("2025-05-01").atTime(rand.nextInt(24), rand.nextInt(60)), 1, 1, 1, "TYPE1"));
        sents.add(new NotificationEvent(LocalDate.parse("2025-05-01").atTime(rand.nextInt(24), rand.nextInt(60)), 1, 1, 1, "TYPE1"));

        BatchNotificationDeduplication batchNotificationDeduplication = new BatchNotificationDeduplication();
        var dups = batchNotificationDeduplication.filterDuplicated(sents);
        for(var dup : dups) {
            System.out.printf("%s %d %d, %s%n", dup.timestamp, dup.userId, dup.orderId, dup.messageType);
        }
    }
}
