package practice.interview.map;

import java.util.HashMap;
import java.util.Map;

public class MapOperations {
    record User(Integer userId, String name) {};

    public static void main(String[] args) {
        Map<Integer, User> users = new HashMap<>();
        // new User(1, "") is added, then user1 is null
        User user1 = users.putIfAbsent(1, new User(1, "1"));
        // nothing happend, user2 is what added in the previous line
        User user2 = users.putIfAbsent(1, new User(1, "2"));

        // user3 is null
        User user3 = users.computeIfAbsent(2, k -> new User(k, "3"));
        // user4 is what added in the preivous line
        User user4 = users.computeIfAbsent(2, k -> new User(k, "4"));

        // user5 is null
        User user5 = users.computeIfPresent(3, (k , v) -> new User(k, "5"));
        // user6 is what added in the preivous line
        User user6 = users.computeIfPresent(3, (k , v) -> new User(k, "6"));
    }
}
