package practice.interview.coupang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

/**
 * Estimated Delivery Delay Detector
 *
 * You are given shipment scan events:
 *
 * [shipment_id, timestamp, status]
 *
 * Statuses may include:
 * PICKED_UP, AT_HUB, OUT_FOR_DELIVERY, DELIVERED
 *
 * A shipment is considered delayed if expected next status does not arrive within a threshold.
 *
 * Write a program to find all delayed shipments as of a given timestamp.
 *
 * Possible follow-ups
 * 	•	Different thresholds by status
 * 	•	Ignore already delivered shipments
 * 	•	Return current stage of delay
 */
public class EstimatedDeliveryDelayDetector {
    private static final Logger log = LoggerFactory.getLogger(EstimatedDeliveryDelayDetector.class);

    public enum DeliveryStatus {
        AT_HUB, PICKED_UP,  OUT_FOR_DELIVERY, DELIVERED
    }

    Map<DeliveryStatus, Integer> threshold = new HashMap<>();

    public record ShipmentEvent(int id, Long timestamp, DeliveryStatus status) {};

    Map<Integer, PriorityQueue<ShipmentEvent>> eventsByShipment = new HashMap<>();

    public EstimatedDeliveryDelayDetector() {
        threshold.put(DeliveryStatus.PICKED_UP, 48);
        threshold.put(DeliveryStatus.OUT_FOR_DELIVERY, 48 + 24);
        threshold.put(DeliveryStatus.DELIVERED, 48 + 24 + 12);
    }

    public void logShipmentEvent(int shipmentId, Long timestamp, DeliveryStatus status) {
        logShipmentEvent(new ShipmentEvent(shipmentId, timestamp, status));
    }

    public void logShipmentEvent(ShipmentEvent event) {
        eventsByShipment.putIfAbsent(event.id, new PriorityQueue<>(
                (s1, s2) -> s1.status.compareTo(s2.status) * -1
        ));
        eventsByShipment.computeIfPresent(event.id, (shipmentId, events) -> {events.add(event); return events;});
    }

    public Long getHoursSpent(Integer shipmentId) {
        return getHoursSpent(eventsByShipment.get(shipmentId));
    }

    public Long getHoursSpent(PriorityQueue<ShipmentEvent> events) {
        Long hoursSpent = 0L;
        PriorityQueue<ShipmentEvent> copied = new PriorityQueue<>(events);
        long base = 0L;
        Long last = copied.poll().timestamp();

        while(!copied.isEmpty()) {
            ShipmentEvent event = copied.poll();
            base += (last - event.timestamp);
        }
        hoursSpent = Double.valueOf ((double )base / 1000L / 60L / 60L).longValue();
        return hoursSpent;
    }

    public DeliveryStatus getLastStatus(Integer shipmentId) {
        return getLastStatus(eventsByShipment.get(shipmentId));
    }

    public DeliveryStatus getLastStatus(PriorityQueue<ShipmentEvent> events) {
        if (events == null) {
            return DeliveryStatus.AT_HUB;
        }

        ShipmentEvent lastEvent = events.peek();
        return lastEvent != null ? lastEvent.status : DeliveryStatus.AT_HUB;
    }

    public static void main(String[] args) {
        EstimatedDeliveryDelayDetector detector = new EstimatedDeliveryDelayDetector();
        LocalDateTime base = LocalDateTime.of(2026, 6, 21, 9, 0,0);

        detector.logShipmentEvent(1, base.minusDays(1).minusHours(12).toInstant(ZoneOffset.UTC).toEpochMilli(), DeliveryStatus.PICKED_UP);
        detector.logShipmentEvent(1, base.minusDays(2).toInstant(ZoneOffset.UTC).toEpochMilli(), DeliveryStatus.AT_HUB);
        detector.logShipmentEvent(1, base.minusHours(14).toInstant(ZoneOffset.UTC).toEpochMilli(), DeliveryStatus.DELIVERED);
        detector.logShipmentEvent(1, base.minusDays(1).minusHours(2).toInstant(ZoneOffset.UTC).toEpochMilli(), DeliveryStatus.OUT_FOR_DELIVERY);

        detector.logShipmentEvent(2, base.minusDays(2).minusHours(12).toInstant(ZoneOffset.UTC).toEpochMilli(), DeliveryStatus.PICKED_UP);
        detector.logShipmentEvent(2, base.minusDays(1).minusHours(2).toInstant(ZoneOffset.UTC).toEpochMilli(), DeliveryStatus.OUT_FOR_DELIVERY);
        detector.logShipmentEvent(2, base.minusDays(4).toInstant(ZoneOffset.UTC).toEpochMilli(), DeliveryStatus.AT_HUB);

        detector.logShipmentEvent(3, base.minusDays(3).minusHours(12).toInstant(ZoneOffset.UTC).toEpochMilli(), DeliveryStatus.PICKED_UP);
        detector.logShipmentEvent(3, base.minusDays(5).toInstant(ZoneOffset.UTC).toEpochMilli(), DeliveryStatus.AT_HUB);

        System.out.println(detector.getLastStatus(1));
        System.out.println(detector.getLastStatus(2));
        System.out.println(detector.getLastStatus(3));

        System.out.println(detector.getHoursSpent(1));
        System.out.println(detector.getHoursSpent(2));
        System.out.println(detector.getHoursSpent(3));
    }
}
