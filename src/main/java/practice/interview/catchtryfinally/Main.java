package practice.interview.catchtryfinally;

public class Main {
    public static void main(String[] args) {
        try {
            FinallyWithException finallyWithException = new FinallyWithException();
            finallyWithException.run();
        } catch (Exception e) {
            // do nothing
        }

        try {
            FinallyWithReturn finallyWithReturn = new FinallyWithReturn();
            finallyWithReturn.run();
        } catch (Exception e) {
            // do nothing
        }

        try {
            FinallyWithReturn finallyWithReturn = new FinallyWithReturn();
            finallyWithReturn.run2();
        } catch (Exception e) {
            // do nothing
        }
    }
}
