package practice.interview.queue;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

public class PriorityQueueOperations {
    class Item implements Comparable<Item> {
        String name;
        LocalDateTime createdAt;

        public Item(final String name) {
            this.name = name;
            this.createdAt = LocalDateTime.now();
        }

        @Override
        public int compareTo(Item o) {
            return this.createdAt.compareTo(o.createdAt);
        }
    }

    PriorityQueue<Item> queue = new PriorityQueue<>();
}
