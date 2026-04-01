## 1. Consecutive Customer Visit Detection

You are given website visit logs in the format:

[date, user_id, page_id]

For a given target date D, find all users who:
•	visited the site on both D and D-1
•	visited at least one page on each day
•	visited a different set of pages on the two days

Return the list of matching users in sorted order.

Possible follow-ups
•	Count only distinct pages per day
•	Ignore duplicate visits to the same page on the same day
•	Generalize to k consecutive days

⸻

## 2. Order State Transition Validator

You are given a stream of order events:

[timestamp, order_id, status]

Possible statuses are:

PLACED, PAID, ALLOCATED, PICKED, PACKED, SHIPPED, DELIVERED, CANCELLED, REFUNDED

Write a program that finds all orders with an invalid state transition.

Examples of invalid transitions:
•	DELIVERED before SHIPPED
•	REFUNDED before PAID
•	PICKED after CANCELLED

Return all invalid order_ids.

Possible follow-ups
•	Handle duplicate events
•	Handle out-of-order events
•	Support partial refund or partial shipment

⸻

## 3. Warehouse Inventory Allocation

You are given:
•	a list of warehouses
•	each warehouse’s available stock for each SKU
•	shipping cost from each warehouse to a customer region
•	estimated delivery time from each warehouse to that region

For each order, choose the best warehouse using:
1.	warehouse must have enough stock
2.	minimize delivery time
3.	if tied, minimize shipping cost

Return the selected warehouse for each order.

Possible follow-ups
•	Split order across multiple warehouses if needed
•	Reserve inventory so two orders do not oversell
•	Recompute if stock updates arrive late

⸻

## 4. Oversell Risk Detector

You are given inventory snapshots and order reservation logs:

[timestamp, sku, available_qty]
[timestamp, order_id, sku, reserved_qty]

Find all SKUs that become oversold at any point in time.

A S KU is oversold if total reserved quantity exceeds latest known available quantity.

Return the list of risky SKUs.

Possible follow-ups
•	Handle delayed snapshot arrival
•	Distinguish between reserved and confirmed stock
•	Report the first timestamp when oversell happened

⸻

## 5. Fulfillment Center Dock Scheduling

A fulfillment center has N unloading docks.

You are given inbound truck reservations:

[truck_id, arrival_time, unload_duration]

Assign trucks to docks so that:
•	no two trucks overlap on the same dock
•	trucks are assigned in arrival order if possible
•	the number of rejected trucks is minimized

Return the dock assignment for each truck, or -1 if it cannot be assigned.

Possible follow-ups
•	Add truck priority
•	Support delayed arrivals
•	Compute peak dock usage by hour

⸻

## 6. Inbound Capacity Breach Detection

You are given truck arrival logs:

[fc_id, date, hour, truck_count]

Each fulfillment center has a maximum hourly unloading capacity.

Find all (fc_id, date, hour) combinations where truck count exceeds capacity.

Return them sorted by date and hour.

Possible follow-ups
•	Compute top 3 most overloaded FCs
•	Predict next-day overload using last 7 days average
•	Distinguish scheduled vs actual arrivals

⸻

## 7. Seller Catalog Deduplication

You are given product listing feeds from multiple sellers:

[seller_id, product_name, brand, category, price]

Different sellers may upload the same product using slightly different names.

Group listings that likely refer to the same product.

Return clusters of duplicate products.

Possible follow-ups
•	Normalize casing and punctuation
•	Use exact brand match plus fuzzy name match
•	Detect conflicting prices within the same cluster

⸻

## 8. Seller Inventory Feed Reconciliation

You are given:
•	seller inventory feed updates
•	actual order deductions

Feed update format:

[timestamp, seller_id, sku, qty]

Order deduction format:

[timestamp, sku, sold_qty]

For each SKU, find whether seller-reported stock is inconsistent with actual deductions.

Return all inconsistent SKUs.

Possible follow-ups
•	Allow small tolerance
•	Report the first mismatch time
•	Support multiple sellers for same SKU

⸻

## 9. Last-Mile Delivery Zone Lookup

You are given:
•	a set of delivery zones
•	each zone contains a list of postal codes
•	orders contain customer postal codes

For each order, determine which delivery zone it belongs to.

If a postal code belongs to no zone, return "UNSERVICEABLE".

Possible follow-ups
•	Support overlapping zones with priority
•	Count orders per zone
•	Find the busiest zone for the day

⸻

## 10. Courier Route Capacity Checker

You are given delivery assignments:

[route_id, stop_id, package_count]

Each route has a maximum package capacity.

Find all routes that exceed capacity.

Also compute how many excess packages must be reassigned.

Possible follow-ups
•	Redistribute excess packages to nearby routes
•	Minimize number of routes changed
•	Prioritize same-zone reassignment

⸻

## 11. Estimated Delivery Delay Detector

You are given shipment scan events:

[shipment_id, timestamp, status]

Statuses may include:
PICKED_UP, AT_HUB, OUT_FOR_DELIVERY, DELIVERED

A shipment is considered delayed if expected next status does not arrive within a threshold.

Write a program to find all delayed shipments as of a given timestamp.

Possible follow-ups
•	Different thresholds by status
•	Ignore already delivered shipments
•	Return current stage of delay

⸻

## 12. Batch Notification Deduplication

You are given notification events:

[timestamp, user_id, channel, message_type, order_id]

A user should not receive the same notification more than once for the same order and message type.

Return only the deduplicated notifications to send.

Possible follow-ups
•	Prioritize SMS over email for urgent messages
•	Respect user notification preferences
•	Limit to at most 3 notifications per user per hour

⸻

## 13. Returns and Refund Reconciliation

You are given three datasets:
•	return request logs
•	warehouse return receipt logs
•	refund logs

Find all orders where:
•	refund was issued without returned item received
•	returned item was received but no refund was issued
•	refund amount does not match returned quantity

Return all mismatched order IDs.

Possible follow-ups
•	Support partial returns
•	Handle multi-item orders
•	Report mismatch reason

⸻

## 14. Fraudulent Order Pattern Detection

You are given order logs:

[timestamp, user_id, card_id, address_id, order_amount]

Flag suspicious users based on rules such as:
•	too many orders in a short time
•	same card used by many users
•	same address used by many cards
•	repeated high-value failed orders

Return suspicious user_ids.

Possible follow-ups
•	Compute fraud score instead of binary flag
•	Add per-country thresholds
•	Detect coordinated abuse groups

⸻

## 15. Refund Abuse Detection

You are given:

[user_id, order_id, item_id, returned, refunded, delivered]

Find users who show abnormal behavior such as:
•	high refund rate
•	many refunds without confirmed return
•	repeated claims on high-value items

Return top K suspicious users.

Possible follow-ups
•	Rank by abuse score
•	Exclude users with very small number of orders
•	Compute refund ratio by category

⸻

## 16. Search-to-Purchase Funnel Analysis

You are given event logs:

[timestamp, user_id, event_type, product_id]

Possible events:
SEARCH, VIEW, ADD_TO_CART, PURCHASE

For a given day, compute:
•	number of users who searched
•	number who viewed
•	number who added to cart
•	number who purchased

Also compute conversion rate between each stage.

Possible follow-ups
•	Compute per category
•	Compute per seller
•	Restrict to same-day funnel only

⸻

## 17. Top-Selling Product per Category per Day

You are given order records:

[date, order_id, product_id, category, quantity]

For each day and category, find the product with the highest total quantity sold.

Return:
[date, category, product_id, total_quantity]

Possible follow-ups
•	Break ties by smallest product_id
•	Return top 3 instead of top 1
•	Support rolling 7-day window

⸻

## 18. Real-Time Active Buyer Window

You are given order or browsing logs:

[timestamp, user_id]

For each minute, compute how many unique users were active in the last 10 minutes.

Return the time series.

Possible follow-ups
•	Support 1-hour sliding window
•	Track both page viewers and buyers separately
•	Optimize for very large log volume

⸻

## 19. Retry-Safe Event Processor

You are given a list of order events that may contain duplicates:

[event_id, order_id, event_type, timestamp]

Each event should be processed exactly once logically, even if it appears multiple times.

Return the final valid event sequence per order after deduplication.

Possible follow-ups
•	Duplicates may have different timestamps
•	Events may arrive out of order
•	Detect conflicting duplicates

⸻

## 20. Multi-Day Royal Customer Detector

You are given visit logs:

[date, user_id, page_id]

A “royal customer” is defined as a user who:
•	visited on at least 2 consecutive days
•	visited different pages on each day
•	had at least N distinct total page visits during the streak

Find all royal customers for a given date range.

Possible follow-ups
•	Find longest streak per user
•	Rank users by streak length, then by distinct page count
•	Generalize to weekly activity
