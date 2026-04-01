package practice.interview.coupang;

/**
 * Seller Inventory Feed Reconciliation
 *
 * You are given:
 * 	•	seller inventory feed updates
 * 	•	actual order deductions
 *
 * Feed update format:
 *
 * [timestamp, seller_id, sku, qty]
 *
 * Order deduction format:
 *
 * [timestamp, sku, sold_qty]
 *
 * For each SKU, find whether seller-reported stock is inconsistent with actual deductions.
 *
 * Return all inconsistent SKUs.
 *
 * Possible follow-ups
 * 	•	Allow small tolerance
 * 	•	Report the first mismatch time
 * 	•	Support multiple sellers for same SKU
 */
public class SellerInventoryFeedReconciliation {
}
