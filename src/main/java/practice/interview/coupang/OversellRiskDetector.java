package practice.interview.coupang;

/**
 * Oversell Risk Detector
 *
 * You are given inventory snapshots and order reservation logs:
 *
 * [timestamp, sku, available_qty]
 * [timestamp, order_id, sku, reserved_qty]
 *
 * Find all SKUs that become oversold at any point in time.
 *
 * A SKU is oversold if total reserved quantity exceeds latest known available quantity.
 *
 * Return the list of risky SKUs.
 *
 * Possible follow-ups
 * 	•	Handle delayed snapshot arrival
 * 	•	Distinguish between reserved and confirmed stock
 * 	•	Report the first timestamp when oversell happened
 */
public class OversellRiskDetector {
}
