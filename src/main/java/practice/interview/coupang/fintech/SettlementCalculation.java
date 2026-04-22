package practice.interview.coupang.fintech;

/**
 * 5. Seller Settlement Calculation
 *
 * Problem
 *
 * You are given order-level financial records:
 *
 * [order_id, seller_id, item_amount, shipping_fee, platform_fee, refunded_amount]
 *
 * For each seller, calculate net payout:
 *
 * net_payout = sum(item_amount + shipping_fee - platform_fee - refunded_amount)
 *
 * Return seller payouts sorted by seller ID.
 *
 * Edge Cases
 * 	•	Refunded amount may be partial
 * 	•	Some orders may have zero shipping fee
 * 	•	Platform fee can vary by order
 *
 * Follow-ups
 * 	•	Add reserve holdback percentage
 * 	•	Support settlement by day
 * 	•	Support different settlement cycles per seller
 */
public class SettlementCalculation {
}
