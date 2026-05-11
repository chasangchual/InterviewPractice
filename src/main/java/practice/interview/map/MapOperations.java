package practice.interview.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapOperations {
    record User(Integer userId, String name) {};

    public static void main(String[] args) {
        Map<Integer, User> users = new HashMap<>();

        // getOrDefault() returns the value in the map if the key exists. Or returns the specified default value
        // Nothing happened inside of the map
        User user2001 = users.getOrDefault(2001, new User(2001, "John"));
        user2001 = users.getOrDefault(2001, new User(2001, "John"));
        users.put(2001, user2001);

        // user2002 is Richard and also Richard is added to the map
        User user2002 = users.computeIfAbsent(2002, k -> new User(k, "Richard"));
        // computeIfAbsent returns the value which mapped with the key. user2002 is still Richard
        user2002 = users.computeIfAbsent(2002, k -> new User(k, "Alex"));

        // 2003 is missing and user2003 is null
        User user2003 = users.computeIfPresent(2003, (k , v) -> new User(k, "Jack"));
        // user2003 becomes Jack
        user2003 = users.computeIfPresent(2002, (k , v) -> new User(k, "Jack"));
    }
}
