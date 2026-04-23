package practice.interview.coupang.fintech;

import java.math.BigDecimal;
import java.util.List;

/**
 * 4. Refund Reconciliation Checker
 *
 * Problem
 * You are given three datasets:
 *
 * Payments
 * [payment_id, order_id, captured_amount]
 *
 * Refunds
 * [refund_id, payment_id, refund_amount]
 *
 * Orders
 * [order_id, order_status]
 *
 * Find all problematic refunds where:
 * 	•	total refund amount exceeds captured amount
 * 	•	refund exists for non-captured payment
 * 	•	refunded order is already cancelled before capture
 *
 * Return all problematic refund_ids with reason.
 *
 * Output
 *
 * Example:
 *
 * [
 *   ["R12", "REFUND_EXCEEDS_CAPTURE"],
 *   ["R15", "PAYMENT_NOT_CAPTURED"]
 * ]
 */
public class RefundReconciliationChecker {
    enum OrderState {
        ORDERED,
        PAYED,
        DELIVERED,
        REFUND_REQUESTED,
        REFUNDED
    }

    enum ReconconcilationError {
        REFUND_EXCEEDS_CAPTURE,
        PAYMENT_NOT_CAPTURED
    }

    record Order(int id, OrderState state) {};
    record Payment(int id, int orderId, BigDecimal capturedAmount) {};
    record Refund(int id, int paymentId, BigDecimal refundAmount) {};
    record ReconcilationResult(int refundId, ReconconcilationError error) {
        @Override
        public String toString() {
            return String.format("%d, %s", refundId, error.toString());
        }
    };

    List<Order> orders = List.of(new Order(1, OrderState.PAYED),
            new Order(2, OrderState.PAYED),
            new Order(3, OrderState.PAYED),
            new Order(4, OrderState.PAYED),
            new Order(5, OrderState.PAYED),
            new Order(6, OrderState.PAYED)
    );
    List<Payment> payments = List.of(new Payment(1, 1, BigDecimal.valueOf(200)),
            new Payment(2, 2, BigDecimal.valueOf(200)),
            new Payment(3, 3, BigDecimal.valueOf(200)),
            new Payment(4, 5, BigDecimal.valueOf(200)),
            new Payment(6, 6, BigDecimal.valueOf(200)),
            new Payment(7, 8, BigDecimal.valueOf(200))
    );

    List<Refund> refunds = List.of(new Refund(1, 1, BigDecimal.valueOf(200)),
            new Refund(2, 2, BigDecimal.valueOf(200)),
            new Refund(3, 3, BigDecimal.valueOf(200)),
            new Refund(4, 5, BigDecimal.valueOf(200)),
            new Refund(6, 6, BigDecimal.valueOf(200)),
            new Refund(7, 8, BigDecimal.valueOf(200))
    );

    public List<ReconcilationResult> runReconciliation() {
        return null;
    }

    public static void main(String[] args) {
        RefundReconciliationChecker refundReconciliationChecker = new RefundReconciliationChecker();
        System.out.println(List.of(refundReconciliationChecker.runReconciliation()));
    }
}
