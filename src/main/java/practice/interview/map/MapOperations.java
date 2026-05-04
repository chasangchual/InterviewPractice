package practice.interview.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapOperations {
    record User(Integer userId, String name) {};

    public static void main(String[] args) {
        Map<Integer, User> users = new HashMap<>();
        // new User(1, "") is added, then user1 is null
        User user1 = users.putIfAbsent(1, new User(1, "1"));
        // Nothing happened. user2 is what is added in the previous step
        User user2 = users.putIfAbsent(1, new User(1, "2"));

        // user3 is a user object with name "3"
        User user3 = users.computeIfAbsent(2, k -> new User(k, "3"));
        // nothing happened. user4 is the same as user 3
        User user4 = users.computeIfAbsent(2, k -> new User(k, "4"));


        // user5 is null
        User user5 = users.computeIfPresent(3, (k , v) -> new User(k, "5"));
        // user6 is what added in the previous line
        User user6 = users.computeIfPresent(3, (k , v) -> new User(k, "6"));

        user5 = users.computeIfAbsent(3, k ->new User(k, "5"));
        user6 = users.computeIfPresent(3, (k , v) -> new User(k, "6"));

        System.out.println(user6.name);

        Map<Integer, List<Integer>> userData = new HashMap<>();
        userData.computeIfAbsent(101, k-> new ArrayList<>()).add(91);
        userData.computeIfAbsent(101, k-> new ArrayList<>()).add(92);
        userData.computeIfAbsent(101, k-> new ArrayList<>()).add(93);
        userData.computeIfAbsent(101, k-> new ArrayList<>()).add(94);
    }
}
