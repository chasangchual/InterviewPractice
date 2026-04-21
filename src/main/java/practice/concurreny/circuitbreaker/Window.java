package practice.concurreny.circuitbreaker;

public class Window {
    private final int size;
    private final boolean[] succeeded;
    private final boolean[] slow;

    private int countSucceed = 0;
    private int countSlow = 0;

    private int total = 0;

    public Window(int size) {
        this.size = size;

        this.succeeded = new boolean[size];
        this.slow = new boolean[size];
    }

    public void set(boolean isSuccess, boolean isSlow) {
        int slot = total % size;

        if (total > size) {
            if (succeeded[slot]) {
                countSucceed--;
            }

            if (slow[slot]) {
                countSlow--;
            }

            succeeded[slot] = !isSuccess;
            slow[slot] = isSlow;
        }
        countSucceed = isSuccess ? countSucceed + 1: countSucceed;
        countSlow = isSlow ? countSlow + 1 : countSlow;
    }

    public WindowState capture() {
        return new WindowState(size, total, countSucceed, countSlow);
    }
}
