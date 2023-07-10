package practice.interview.queue;

import java.util.LinkedList;
import java.util.Queue;

public class DropAndPullCards {
    public static void dropAndPull(final int size) {
        LinkedList deck = new LinkedList();

        for(int i = size; i > 0; i--) {
            deck.push(i);
        }

        while(deck.size() > 1) {
            deck.poll();
            if(deck.size() > 1) {
                deck.push(deck.poll());
            }
        }
        System.out.println(deck.poll());
    }

    public static void main(String[] args) {
        DropAndPullCards.dropAndPull(16);
    }
}
