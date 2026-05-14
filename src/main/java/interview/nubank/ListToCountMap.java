package interview.nubank;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListToCountMap {
    public Map<Integer, Integer> getCount(List<Integer> ids) {
        return ids.stream().collect(Collectors.toMap(i -> i, i -> 1, (a, b) ->a+b));
    }

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        List<Integer> ids = new ArrayList<>();

        IntStream.range(0, 1000).forEach(i -> {
            ids.add(random.nextInt(30));
        });

        ListToCountMap listToCountMap = new ListToCountMap();
        listToCountMap.getCount(ids).entrySet().stream().forEach(e -> System.out.println(String.format("id: %d, count: %d", e.getKey(), e.getValue())));
    }
}
