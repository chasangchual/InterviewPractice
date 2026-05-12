package practice.interview.queue;

import java.time.LocalDateTime;
import java.util.*;

public class DequeueOperator {
    class Item {
        String name;
        LocalDateTime createdAt;

        public Item(String name) {
            this.name = name;
            this.createdAt = LocalDateTime.now();
        }
    }

    Deque<Item> queue = new ArrayDeque<>();

    public static void main(String[] args) {

    }
}
