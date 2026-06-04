package practice.interview.coupang;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Multi-Day Royal Customer Detector
 * <p>
 * You are given visit logs:
 * <p>
 * [date, user_id, page_id]
 * <p>
 * A “royal customer” is defined as a user who:
 * •	visited on at least 2 consecutive days
 * •	visited different pages on each day
 * •	had at least N distinct total page visits during the streak
 * <p>
 * Find all royal customers for a given date range.
 * <p>
 * Possible follow-ups
 * •	Find longest streak per user
 * •	Rank users by streak length, then by distinct page count
 * •	Generalize to weekly activity
 */
public class MultiDayRoyalCustomerDetector {
    static class UserVisitLog {
        int userId;
        int pageId;

        public UserVisitLog(int userId, int pageId) {
            this.userId = userId;
            this.pageId = pageId;
        }

        public int getUserId() {
            return userId;
        }

        public int getPageId() {
            return this.pageId;
        }

    }

    public Set<Integer> getRoyalCustomer(Map<LocalDate, List<UserVisitLog>> userVisits) {
        Map<LocalDate, Map<Integer, Set<Integer>>> pagesByUserPerDate = new HashMap<>();
        // build

        userVisits.entrySet().stream().forEach(e -> {
                LocalDate localDate = e.getKey();
                List<UserVisitLog> userVisitLogs = e.getValue();

                if (!pagesByUserPerDate.containsKey(localDate)) {
                    pagesByUserPerDate.put(localDate, new HashMap<>());
                }

                Map<Integer, Set<Integer>> pagesByUser = pagesByUserPerDate.get(localDate);

                userVisitLogs.stream().forEach(log -> {
                    if (!pagesByUser.containsKey(log.getUserId())) {
                        pagesByUser.put(log.getUserId(), new HashSet<>());
                    }
                    Set<Integer> pages = pagesByUser.get(log.getUserId());
                    pages.add(log.getPageId());
                    pagesByUser.put(log.getUserId(), pages);
                });

                pagesByUserPerDate.put(localDate, pagesByUser);
            }
        );

        Set<Integer> royalCuster = new HashSet<>();

        List<LocalDate> dates = pagesByUserPerDate.keySet().stream().sorted().collect(Collectors.toList());

        for(int i = 0 ; i < dates.size() - 1; i++) {
            LocalDate previous = dates.get(i);
            LocalDate current = dates.get(i+1);

            Map<Integer, Set<Integer>> prevPagesByUser = pagesByUserPerDate.get(previous);
            Map<Integer, Set<Integer>> currPagesByUser = pagesByUserPerDate.get(current);

            prevPagesByUser.entrySet().stream().forEach(e -> {
                // if the current has the same user
                // e.getKey() is user
                Integer userId = e.getKey();

                if(currPagesByUser.containsKey(userId)) {
                    Set<Integer> prevPages = e.getValue();
                    // look through all pages who visited the page of e.getKey()
                    if(prevPages.stream().filter(page -> currPagesByUser.get(userId).contains(page)).toList().size() > 2); {
                        royalCuster.add(e.getKey());
                    }
                }
            });

        }

        return royalCuster;
    }

    public static void main(String[] args) {
        Map<LocalDate, List<UserVisitLog>> userVisits = new HashMap<>();
        LocalDate ofYesterday = LocalDate.of(2026, 1, 25);
        LocalDate ofToday = LocalDate.of(2026, 1, 26);

        userVisits.put(ofYesterday, new ArrayList<>());
        userVisits.put(ofToday, new ArrayList<>());

        List<UserVisitLog> logOfYesterday = new ArrayList<UserVisitLog>();
        logOfYesterday.add(new UserVisitLog(1, 1));
        logOfYesterday.add(new UserVisitLog(1, 3));
        logOfYesterday.add(new UserVisitLog(1, 5));
        logOfYesterday.add(new UserVisitLog(1, 3));
        logOfYesterday.add(new UserVisitLog(1, 5));
        logOfYesterday.add(new UserVisitLog(2, 1));
        logOfYesterday.add(new UserVisitLog(2, 3));
        logOfYesterday.add(new UserVisitLog(2, 5));
        logOfYesterday.add(new UserVisitLog(2, 2));
        logOfYesterday.add(new UserVisitLog(2, 4));
        logOfYesterday.add(new UserVisitLog(3, 2));
        logOfYesterday.add(new UserVisitLog(3, 4));
        logOfYesterday.add(new UserVisitLog(3, 8));
        logOfYesterday.add(new UserVisitLog(4, 2));
        logOfYesterday.add(new UserVisitLog(4, 4));
        logOfYesterday.add(new UserVisitLog(4, 8));

        List<UserVisitLog> logOfToday = new ArrayList<UserVisitLog>();
        logOfToday.add(new UserVisitLog(11, 1));
        logOfToday.add(new UserVisitLog(11, 3));
        logOfToday.add(new UserVisitLog(11, 5));
        logOfToday.add(new UserVisitLog(11, 3));
        logOfToday.add(new UserVisitLog(11, 5));
        logOfToday.add(new UserVisitLog(2, 1));
        logOfToday.add(new UserVisitLog(2, 3));
        logOfToday.add(new UserVisitLog(2, 5));
        logOfToday.add(new UserVisitLog(2, 2));
        logOfToday.add(new UserVisitLog(2, 4));
        logOfToday.add(new UserVisitLog(13, 2));
        logOfToday.add(new UserVisitLog(13, 4));
        logOfToday.add(new UserVisitLog(13, 8));
        logOfToday.add(new UserVisitLog(14, 2));
        logOfToday.add(new UserVisitLog(14, 4));
        logOfToday.add(new UserVisitLog(14, 8));
        logOfToday.add(new UserVisitLog(4, 4));
        logOfToday.add(new UserVisitLog(4, 8));

        userVisits.put(ofYesterday, logOfYesterday);
        userVisits.put(ofToday, logOfToday);

        MultiDayRoyalCustomerDetector detector = new MultiDayRoyalCustomerDetector();
        System.out.println(detector.getRoyalCustomer(userVisits));
    }
}
