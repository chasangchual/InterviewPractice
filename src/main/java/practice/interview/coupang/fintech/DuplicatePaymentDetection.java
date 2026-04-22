package practice.interview.coupang.fintech;

/**
 * Problem
 *
 * You are given payment authorization logs in the format:
 *
 * [timestamp, user_id, order_id, payment_id, amount, status]
 *
 * A duplicate payment is defined as:
 * 	•	same user_id
 * 	•	same order_id
 * 	•	same amount
 * 	•	multiple successful authorizations within 10 minutes
 *
 * Write a function that returns all duplicate payment_ids.
 *
 * Requirements
 * 	•	Only SUCCESS authorizations count
 * 	•	The first successful authorization is valid
 * 	•	Any later successful authorization within 10 minutes should be flagged as duplicate
 * 	•	Return flagged payment_ids sorted by timestamp
 */
public class DuplicatePaymentDetection {
}
