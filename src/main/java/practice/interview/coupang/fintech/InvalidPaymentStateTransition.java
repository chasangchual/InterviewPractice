package practice.interview.coupang.fintech;

/**
 * 2. Invalid Payment State Transition
 *
 * Problem
 *
 * You are given payment lifecycle events:
 *
 * [timestamp, payment_id, event_type]
 *
 * Valid event types:
 * 	•	AUTHORIZED
 * 	•	CAPTURED
 * 	•	CANCELLED
 * 	•	REFUNDED
 * 	•	FAILED
 *
 * Write a function to find all payment_ids with invalid transitions.
 *
 * Rules
 * 	•	CAPTURED must come after AUTHORIZED
 * 	•	REFUNDED must come after CAPTURED
 * 	•	CANCELLED cannot happen after CAPTURED
 * 	•	Once FAILED, no further transitions are allowed
 * 	•	Duplicate same-state events should be ignored
 */
public class InvalidPaymentStateTransition {
}
