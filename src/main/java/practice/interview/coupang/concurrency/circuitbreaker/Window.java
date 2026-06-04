package practice.interview.coupang.concurrency.circuitbreaker;

import java.util.*;

public class Window {
    private final int size;
    private final Boolean[] bucket;
    private int total = 0;
    private int success = 0;

    public Window(int size) {
        this.size = size;
        bucket = new Boolean[size];
    }

    public synchronized void setSuccess(Boolean isSuccess) {
        int pos = total % size;
        if (total > size) {
            success = bucket[pos] ? success - 1 : success;
        }
        bucket[pos] = isSuccess;
        success = isSuccess ? success + 1 : success;
        total ++;
    }

    public int getSuccess() {
        return this.success;
    }

    public int getTotal() {
        return this.total;
    }

    public double getSuccessRate() {
        if(total < size) {
            return ((double) success / total);
        }
        return ((double) success / size);
    }
}
