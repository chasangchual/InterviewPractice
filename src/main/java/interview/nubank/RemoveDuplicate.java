package interview.nubank;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RemoveDuplicate {
    record UserLog(Integer id, Integer userId, Integer pageId, LocalDateTime timestamp) {
    }

    /**
     * Finds users who visited at least one same page on two consecutive days.
     * <p>
     * A "royal customer" is defined here as a user who:
     * 1. has visits on two consecutive dates, and
     * 2. visited at least one common page on both of those dates.
     */
    public List<Integer> findRoyalCustomer(List<UserLog> userVisitLogs) {
        var visitLogPerUserId = userVisitLogs.stream().collect(Collectors.toMap(
                log -> log.userId,
                log -> List.of(log),
                (existing, append) ->
                        Stream.concat(existing.stream(), append.stream()).toList()
        ));

        var visitedLogByDateByUser = visitLogPerUserId.entrySet().stream().collect(Collectors.toMap(
                logByUser -> logByUser.getKey(),
                logByUser -> breakDownUserVisitByDate(logByUser.getValue())
        ));

        return visitedLogByDateByUser.entrySet().stream()
                .filter(e -> isRoyalCustomer(e.getValue()))
                .map(e -> e.getKey())
                .toList();
    }

    /**
     * Checks whether a user's date-based page visits satisfy the royal customer rule.
     * <p>
     * Input structure:
     * visitDate -> set of pageIds visited on that date
     * <p>
     * The method sorts all visited dates, then checks each pair of neighboring dates.
     * If two neighboring dates are consecutive and share at least one pageId,
     * the user is considered a royal customer.
     */
    private boolean isRoyalCustomer(Map<LocalDate, Set<Integer>> visitedPagesByDate) {
        List<LocalDate> visitedDates = visitedPagesByDate.keySet().stream().sorted((a, b) -> a.compareTo(b)).toList();
        boolean isRoyalCustomer = false;

        for (int i = 0; i < visitedDates.size() - 1; i++) {
            LocalDate curr = visitedDates.get(i);
            LocalDate next = visitedDates.get(i + 1);
            if (curr.plusDays(1).compareTo(next) == 0) {
                Set<Integer> currVisitedPages = visitedPagesByDate.get(curr);
                Set<Integer> nextVisitedPages = visitedPagesByDate.get(next);
                if (currVisitedPages.stream()
                        .filter(currPage -> nextVisitedPages.contains(currPage))
                        .findFirst()
                        .isPresent()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Converts a user's raw visit logs into a date-based page visit map.
     * <p>
     * Example output:
     * 2026-05-01 -> [10, 20, 30]
     * 2026-05-02 -> [20, 40]
     * <p>
     * Multiple visits to the same page on the same date are deduplicated by using a Set.
     */
    public Map<LocalDate, Set<Integer>> breakDownUserVisitByDate(List<UserLog> uservisits) {
        return uservisits.stream().collect(Collectors.toMap(
                visit -> visit.timestamp.toLocalDate(),
                visit -> Set.of(visit.pageId),
                (existingPages, newPages) ->
                        Stream.concat(existingPages.stream(), newPages.stream())
                                .collect(Collectors.toSet())
        ));
    }

    public static void main(String[] args) {
        List<UserLog> userVisitLogs = new ArrayList<>();

        SecureRandom random = new SecureRandom();
        LocalDateTime now = LocalDateTime.now();
        IntStream.range(0, 10000).forEach(i -> {
                    userVisitLogs.add(new UserLog(i, random.nextInt(200),
                            random.nextInt(200),
                            now.minusDays(random.nextInt(30))));
                }
        );
        RemoveDuplicate removeDuplicate = new RemoveDuplicate();
        List<Integer> royalCustomers = removeDuplicate.findRoyalCustomer(userVisitLogs);
        System.out.println(Arrays.toString(royalCustomers.toArray()));

        var visitLogPerUserId = userVisitLogs.stream().collect(Collectors.toMap(
                log -> log.userId,
                log -> List.of(log),
                (existing, append) ->
                        Stream.concat(existing.stream(), append.stream()).toList()
        ));
    }
}
