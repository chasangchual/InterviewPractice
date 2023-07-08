package practice.interview.arrays;

public class SumOfContinuousNumbers {
    public static Integer addConsecutiveNums(final Integer start, final Integer end) {
        Integer len = end - start + 1;
        return len % 2 == 0 ? (end + start) * (len / 2) : (end + start) * (len / 2) + ((end + start) / 2);
    }

    public static void continuousNumbers(final Integer num) {
        Integer start = 1;
        Integer end = 1;
        Integer sum = addConsecutiveNums(start, end);

        while(end <= num) {
            if(sum < num) {
                end ++;
                sum +=  end;
            }  else if (sum > num) {
                sum -= start;
                start ++;
            } else {
                System.out.println(start + " <-> " + end);
                end ++;
                sum += end;
            }
        }
    }

    public static void main(String[] args) {
        SumOfContinuousNumbers.continuousNumbers(15);
    }
}
