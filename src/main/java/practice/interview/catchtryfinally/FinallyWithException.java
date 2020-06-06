package practice.interview.catchtryfinally;

public class FinallyWithException {
    public void run() {
        try {
            throw new IllegalArgumentException("FinallyWithException - no way");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("FinallyWithException");
        } finally {
            System.out.println("FinallyWithException finally");
        }
    }}
