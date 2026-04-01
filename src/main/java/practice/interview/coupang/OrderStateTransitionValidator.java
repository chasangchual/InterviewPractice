package practice.interview.coupang;

/**
 * Order State Transition Validator
 *
 * You are given a stream of order events:
 *
 * [timestamp, order_id, status]
 *
 * Possible statuses are:
 *
 * PLACED, PAID, ALLOCATED, PICKED, PACKED, SHIPPED, DELIVERED, CANCELLED, REFUNDED
 *
 * Write a program that finds all orders with an invalid state transition.
 *
 * Examples of invalid transitions:
 * 	•	DELIVERED before SHIPPED
 * 	•	REFUNDED before PAID
 * 	•	PICKED after CANCELLED
 *
 * Return all invalid order_ids.
 *
 * Possible follow-ups
 * 	•	Handle duplicate events
 * 	•	Handle out-of-order events
 * 	•	Support partial refund or partial shipment3
 */
public class OrderStateTransitionValidator {
}
