package practice.interview.arrays;

public class ScoreCheat {

    public static Double newAverage(final int len, final String oldScore) {
        if(len < 1 || len > 1000 ) {
            throw new IllegalArgumentException();
        }

        String scores[] = oldScore.split(" ");
        if(len != scores.length) {
            throw new IllegalArgumentException();
        }

        Integer max = Integer.parseInt(scores[0]);
        Double sum = Double.valueOf(0);

        for (String score : scores) {
            if(Integer.parseInt(score) > max) {
                max = Integer.parseInt(score);
            }
            sum += Double.valueOf(score);
        }

        return ((sum / max) * 100.0) / len;
    }

    public static void main(final String args[]) {
        System.out.println(ScoreCheat.newAverage(3, "40 80 60"));
        System.out.println(ScoreCheat.newAverage(3, "10 20 30"));
        System.out.println(ScoreCheat.newAverage(4, "1 100 100 100"));
        System.out.println(ScoreCheat.newAverage(5, "1 2 4 8 16"));
        System.out.println(ScoreCheat.newAverage(2, "3 10"));
    }
}
