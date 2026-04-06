package practice.interview.coupang;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Consecutive Customer Visit Detection
 *
 * You are given website visit logs in the format:
 *
 * [date, user_id, page_id]
 *
 * For a given target date D, find all users who:
 * 	•	visited the site on both D and D-1
 * 	•	visited at least one page on each day
 * 	•	visited a different set of pages on the two days
 *
 * Return the list of matching users in sorted order.
 *
 * Possible follow-ups
 * 	•	Count only distinct pages per day
 * 	•	Ignore duplicate visits to the same page on the same day
 * 	•	Generalize to k consecutive days
 */
public class ConsecutiveCustomerVisitDetection {
    record Log(LocalDate timestamp, int userId, int pageId) {};
    public void find(List<Log> logs ) {
        Map<Integer, Map<LocalDate, Set<Integer>>> visits = new HashMap<>();

        for(Log log : logs) {
            if(!visits. containsKey(log.userId)) {
                visits.put(log.userId, new TreeMap<>());
            }

            Map<LocalDate, Set<Integer>> userVisit = visits.get(log.userId);

            if(!userVisit.containsKey(log.timestamp)) {
                userVisit.put(log.timestamp, new HashSet<>());
            }
            userVisit.get(log.timestamp).add(log.pageId);
        }

        for(Map.Entry<Integer, Map<LocalDate, Set<Integer>>> entry : visits.entrySet()) {
            if(isRoyaltyMember(entry)) {
                System.out.println(entry.getKey());
            }
        }

    }

    private boolean isRoyaltyMember(Map.Entry<Integer, Map<LocalDate, Set<Integer>>> entry) {
        if(entry.getValue().size() <= 1) {
            return false;
        }

        boolean isRoyaltyMember = false;
        int days = 0;
        Map.Entry<LocalDate, Set<Integer>>  today = null;
        Map.Entry<LocalDate, Set<Integer>>  tomorrow;
        for(Map.Entry<LocalDate, Set<Integer>> dayVisit : entry.getValue().entrySet()) {
            if(days == 0) {
                today = dayVisit;
            } else {
                tomorrow = dayVisit;

                if(today.getKey().plusDays(1).equals(tomorrow.getKey())) {
                    for(int page : today.getValue()) {
                        if(tomorrow.getValue().contains(page)) {
                            return true;
                        }
                    }
                }
                today = tomorrow;
            }
            days ++;
        }
        return isRoyaltyMember;
    }

    public static void main(String[] args) {
        List<Log> logs = new ArrayList<>();
        logs.add(new Log(LocalDate.parse("2025-05-01"), 1001, 1));
        logs.add(new Log(LocalDate.parse("2025-05-01"), 1002, 2));
        logs.add(new Log(LocalDate.parse("2025-05-01"), 1003, 3));
        logs.add(new Log(LocalDate.parse("2025-05-01"), 2001, 4));
        logs.add(new Log(LocalDate.parse("2025-05-01"), 2001, 1));
        logs.add(new Log(LocalDate.parse("2025-05-01"), 1001, 9));
        logs.add(new Log(LocalDate.parse("2025-05-01"), 1003, 8));
        logs.add(new Log(LocalDate.parse("2025-05-01"), 1009, 7));
        logs.add(new Log(LocalDate.parse("2025-05-01"), 3001, 6));

        logs.add(new Log(LocalDate.parse("2025-05-02"), 1001, 3));
        logs.add(new Log(LocalDate.parse("2025-05-02"), 1002, 1));
        logs.add(new Log(LocalDate.parse("2025-05-02"), 1003, 4));
        logs.add(new Log(LocalDate.parse("2025-05-02"), 2001, 1));
        logs.add(new Log(LocalDate.parse("2025-05-02"), 3001, 2));
        logs.add(new Log(LocalDate.parse("2025-05-02"), 3001, 8));
        logs.add(new Log(LocalDate.parse("2025-05-02"), 3001, 6));
        logs.add(new Log(LocalDate.parse("2025-05-02"), 4001, 9));
        logs.add(new Log(LocalDate.parse("2025-05-02"), 4001, 9));
        logs.add(new Log(LocalDate.parse("2025-05-02"), 4001, 1));

        ConsecutiveCustomerVisitDetection consecutiveCustomerVisitDetection = new ConsecutiveCustomerVisitDetection();
        consecutiveCustomerVisitDetection.find(logs);
    }
}
