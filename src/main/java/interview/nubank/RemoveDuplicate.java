package interview.nubank;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RemoveDuplicate {

//    // find users who visited sequence days different page
//    public List<Integer> findRoyalCustomer(List<UserLog> userLogs) {
//        List<Integer> customers = new ArrayList<>();
//        Map<Integer, Map<Integer, LocalDateTime>> userVisitedPage = new HashMap<>();
//
//        userLogs.stream().collect(Collectors.toMap(log -> log.userId,
//                log -> {
//                    Map<LocalDate, List<Integer>> logByDate = new HashMap<>();
//                    List<Integer> logs = new ArrayList<>();
//                    logs.add(log.pageId);
//                    logByDate.put(log.timestamp.toLocalDate(), logs);
//                    return logByDate;
//                },
//                (existing, replacement) -> {
//                    replacement.entrySet().stream().forEach(
//                            entry -> {
//                                if(existing.containsKey(entry.getKey())) {
//                                    entry.getValue().stream().forEach(
//                                            page -> {
//                                                if(!existing.get(entry.getKey()).contains(page)) {
//                                                    existing.get(entry.getKey()).add(page);
//                                                }
//                                            }
//                                    );
//                                } else {
//                                    existing.put(entry.getKey(), entry.getValue());
//                                }
//                            }
//                    );
//                    return existing;
//                }));
//        return customers;
//    }
    record UserLog(Integer id, Integer userId, Integer pageId, LocalDateTime timestamp) {
    }

    public List<Integer> findRoyalCustomer(List<UserLog> userLogs) {

    }
    public static void main(String[] args) {
        List<UserLog> userLogs = new ArrayList<>();

        SecureRandom random = new SecureRandom();
        LocalDateTime now = LocalDateTime.now();
        IntStream.range(0, 1000).forEach(i -> {
                    userLogs.add(new UserLog(i, random.nextInt(50), random.nextInt(10), now.minusDays(random.nextInt(4))));
                }
        );
        RemoveDuplicate removeDuplicate = new RemoveDuplicate();
        removeDuplicate.findRoyalCustomer(userLogs);
    }
}
