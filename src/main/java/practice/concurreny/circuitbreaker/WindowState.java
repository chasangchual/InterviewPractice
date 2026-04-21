package practice.concurreny.circuitbreaker;

public record WindowState(int size, int total, int success, int slow) {

    public double getSuccessRate() {
        return ((double) success / size);
    }

    public double getSlowRate() {
        return ((double) slow / size);
    }
}
