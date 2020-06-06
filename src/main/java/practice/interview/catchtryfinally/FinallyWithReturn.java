package practice.interview.catchtryfinally;

public class FinallyWithReturn {
    public void run() {
        try {
            throw new IllegalArgumentException("FinallyWithReturn - no way #1");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            System.out.println("FinallyWithReturn finally #1");
        }
    }

    public void run2() {
        try {
            try {
                throw new IllegalArgumentException("FinallyWithReturn - no way #2");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            System.out.println("FinallyWithReturn finally #2");
        }
    }
}
