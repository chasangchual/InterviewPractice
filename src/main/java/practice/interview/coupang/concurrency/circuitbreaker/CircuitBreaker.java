package practice.interview.coupang.concurrency.circuitbreaker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.concurrent.Callable;

public class CircuitBreaker {
    enum CircuitState {
        CLOSE,
        HALF_OPEN,
        OPEN
    }

    static Double THRESHOLD = 0.8;
    static Duration WAIT_UNTIL_HALF_OPEN = Duration.ofMillis(200);
    static Duration WAIT_UNTIL_CLOSE = Duration.ofMillis(400);

    CircuitState state = CircuitState.CLOSE;

    Window window = new Window(100);
    long lastCircuitOpen;

    public <T> T execute(Callable<T> action) throws Exception {
        // verify if the circuit is close or half-open
        if(!isGoodToGo()) {
            // Open circuit
            throw new RuntimeException();
        }

        try {
            T result =  action.call();
            window.setSuccess(Boolean.TRUE);
            onSuccess();

            return result;
        } catch (Exception e) {
            // handle the failures
            window.setSuccess(Boolean.FALSE);
            onFailure();
            throw e;
        }
    }

    private void onSuccess() {
        long now = System.currentTimeMillis();

        if(state.equals(CircuitState.OPEN)) {
            lastCircuitOpen = now;
            state = CircuitState.HALF_OPEN;
        }

        if(state.equals(CircuitState.HALF_OPEN)) {
            if(lastCircuitOpen + WAIT_UNTIL_CLOSE.toMillis() > now) {
                state = CircuitState.CLOSE;
            }
        }
    }

    private void onFailure() {
        if(state.equals(CircuitState.HALF_OPEN)) {
            lastCircuitOpen = System.currentTimeMillis();
            state = CircuitState.OPEN;
        }
    }

    private boolean isGoodToGo() {
        boolean isOverThreshold =  window.getSuccessRate() > THRESHOLD;
        long now = System.currentTimeMillis();

        if(state.equals(CircuitState.CLOSE)) {
            if(isOverThreshold) {
                return true;
            } else {
                state = CircuitState.OPEN;
                lastCircuitOpen = now;
                return false;
            }
        }

        if(state.equals(CircuitState.HALF_OPEN)) {
            if(lastCircuitOpen + WAIT_UNTIL_CLOSE.toMillis() > now) {
                state = CircuitState.CLOSE;
            }
            return true;
        }

        if(state.equals(CircuitState.OPEN)) {
            if(lastCircuitOpen + WAIT_UNTIL_HALF_OPEN.toMillis() > now) {
                state = CircuitState.HALF_OPEN;
                return true;
            }
        }

        return false;
    }
}
