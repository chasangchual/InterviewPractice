package practice.interview.coupang;

/**
 * Courier Route Capacity Checker
 *
 * You are given delivery assignments:
 *
 * [route_id, stop_id, package_count]
 *
 * Each route has a maximum package capacity.
 *
 * Find all routes that exceed capacity.
 *
 * Also compute how many excess packages must be reassigned.
 *
 * Possible follow-ups
 * 	•	Redistribute excess packages to nearby routes
 * 	•	Minimize number of routes changed
 * 	•	Prioritize same-zone reassignment
 */
public class CourierRouteCapacityChecker {
    record Delivery(int routeId, int stopId, int package_count) {};


}
