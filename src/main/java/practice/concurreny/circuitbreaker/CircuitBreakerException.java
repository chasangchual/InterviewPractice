package practice.concurreny.circuitbreaker;

public class CircuitBreakerException extends RuntimeException{
    public CircuitBreakerException(String message) {
        super(message);
    }
}
