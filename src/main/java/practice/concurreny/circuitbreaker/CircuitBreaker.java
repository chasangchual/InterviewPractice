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

    private State current = State.CLOSED;
    private LocalDateTime openedAt ;
    private LocalDateTime halfOpenedAt ;
    private int MAX_TRIES_DURING_HALF_OPEN = 10;
    private final Duration slowThreshold = Duration.ofMillis(200);
    private final Window window ;

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

        try {
            T result = action.call();
            return result;
        } finally {
        }
    }

    private boolean isGoodToGo() {
        var state = window.capture();
        if(state.total() < state.size()) {
            return true;
        }

        double successRate = state.getSuccessRate();
        double slowRate = state.getSlowRate();
        if(successRate > 0.7 || slowRate > 0.3) {
            return false;
        }
        return true;
    }

    private boolean
    
}
