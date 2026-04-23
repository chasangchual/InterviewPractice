package practice.concurreny.circuitbreaker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class CircuitBreaker {
    enum State {
        OPEN,
        HALF_OPEN,
        CLOSED
    }

    private State currentState = State.CLOSED;
    private LocalDateTime openedAt ;

    private LocalDateTime halfOpenedAt ;
    private int successInHaldOpen = 0;

    private int MAX_TRIES_DURING_HALF_OPEN = 10;
    private final Duration slowThreshold = Duration.ofMillis(200);
    private final Window window ;

    private final Duration keepHalfOpen = Duration.ofMillis(800);

    public CircuitBreaker() {
        window = new Window(30);
    }

    public <T> T invoke(Callable<T> action) throws Exception {
        if(!isGoodToGo()) {
            throw new CircuitBreakerException("");
        }

        LocalDateTime startedAt = null;
        boolean isSuccess = false;

        try {
            startedAt = LocalDateTime.now();
            T result = action.call();
            isSuccess = true;
            return result;
        } finally {
            LocalDateTime completedAt = LocalDateTime.now();
            Boolean isSlow = false;
            if(startedAt.plus(slowThreshold).compareTo(completedAt) < 0) {
                isSlow = true;
            }
            // record success and slowness
            window.set(isSuccess, isSlow);
        }
    }

    public <T> T invoke(Callable<T> action, Callable<T> fallback) throws Exception {
        if(!isGoodToGo()) {
            return fallback.call();
        }

        LocalDateTime startedAt = null;
        boolean isSuccess = false;

        try {
            startedAt = LocalDateTime.now();
            T result = action.call();
            isSuccess = true;
            return result;
        } finally {
            LocalDateTime completedAt = LocalDateTime.now();
            Boolean isSlow = false;
            if(startedAt.plus(slowThreshold).compareTo(completedAt) < 0) {
                isSlow = true;
            }
            // record success and slowness
            window.set(isSuccess, isSlow);
        }
    }

    private boolean isGoodToGo() {
        boolean isGoodState = isGoodState();

        if (currentState.equals(State.CLOSED)) {
            if (isGoodState == Boolean.TRUE) {
                return true;
            } else {
                currentState = State.OPEN;
                return false;
            }
        }

        if (currentState.equals(State.OPEN)) {
            if (isGoodState == Boolean.FALSE) {
                return false;
            } else {
                successInHaldOpen = 0;
                currentState = State.HALF_OPEN;
                halfOpenedAt = LocalDateTime.now();
            }
        }

        if (currentState.equals(State.HALF_OPEN)) {
            if (isGoodState == Boolean.TRUE) {
                successInHaldOpen ++;
                LocalDateTime now = LocalDateTime.now();
                if(now.compareTo(halfOpenedAt.plus(keepHalfOpen)) > 0) {
                    currentState = State.CLOSED;
                    return true;
                }
                if(successInHaldOpen <= MAX_TRIES_DURING_HALF_OPEN) {
                    return true;
                }
                return false;
            } else {
                currentState = State.OPEN;
                return false;
            }
        }
        return false;
    }

    private boolean isGoodState() {
        var state = window.capture();
        if(state.total() < state.size()) {
            return true;
        }

        double successRate = state.getSuccessRate();
        double slowRate = state.getSlowRate();
        if(successRate < 0.95 || slowRate > 0.1) {
            return false;
        }
        return true;
    }
    
}
