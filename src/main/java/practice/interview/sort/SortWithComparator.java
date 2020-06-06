package practice.interview.sort;

import java.util.Comparator;

public class SortWithComparator {
    public static void main(String[] args) {

    }

    class Player {
        int score;
        String name;

        public Player() {

        }

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public int getScore() {
            return this.score;
        }

        public String getName() {
            return this.name;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public void setName(String name) {
            this.score = score;
        }
    }

    class Checker implements Comparator<Player> {

        @Override
        public int compare(Player o1, Player o2) {
            if(o1 == null || o2 == null) {
                throw new NullPointerException("null object passed");
            }

            return o1.getScore() != o2.getScore() ? o1.getScore() - o2.getScore() :
                    o1.getName().compareTo(o2.getName());
        }
    }
}
