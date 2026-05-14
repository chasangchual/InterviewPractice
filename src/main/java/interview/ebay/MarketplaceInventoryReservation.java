package interview.ebay;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Implement an inventory reservation system.
 *
 * Operations:
 * - addInventory(itemId, quantity)
 * - reserve(itemId, quantity, reservationId)
 * - confirm(reservationId)
 * - cancel(reservationId)
 * - getAvailable(itemId)
 */
public class MarketplaceInventoryReservation {
    record Reservation(Integer id, Integer itemId, Integer quantity, LocalDateTime reservedAt) {} ;

    class Item {
        private final Integer id;
        Integer state;
        Integer quantity;
        public  Item(Integer id, Integer state, Integer quantity) {
            this.id = id;
            this.state = state;
            this.quantity = 0;
        }

        public Item add(Integer quantity) {
            this.quantity += quantity;
            return this;
        }
    };

    Map<Integer, Item> inventory = new HashMap<>();
    Map<Integer, Reservation> reservations = new HashMap<>();

    public void addInventory(Integer itemId, Integer quantity) {
        inventory.put(itemId, inventory.getOrDefault(itemId, new Item(itemId, 1, 0)).add(quantity));
    }

    public Map<Integer, Item> getInventory() {

        return this.inventory;
    }
    public Boolean reserve(Integer itemId, Integer quantity, Integer reservationId) {
        LocalDateTime now = LocalDateTime.now();

        Reservation reserved = reservations.computeIfAbsent(reservationId, k->new Reservation(k, itemId, quantity, now));
        return reserved.reservedAt.compareTo(now) == 0;
    }

    public Boolean confirm(Integer reservationId) {
        if(!reservations.containsKey(reservationId)) {
            throw new IllegalArgumentException(String.format("reservation: %d is not found", reservationId));
        }

        getReservedByOthers(reservationId);

        removeReservation(reservationId);
        return true;
    }

    public Boolean cancel(Integer reservationId) {
        if(!reservations.containsKey(reservationId)) {
            throw new IllegalArgumentException(String.format("reservation: %d is not found", reservationId));
        }

        reservations.remove(reservationId);
        return true;
    }

    private void removeReservation(Integer reservationId) {
        reservations.remove(reservationId);
    }

    public Boolean isAvailable(Integer reservationId) {
        if(!reservations.containsKey(reservationId)) {
            throw new IllegalArgumentException(String.format("reservation: %d is not found", reservationId));
        }

        Integer itemId = reservations.get(reservationId).itemId();
        Integer total = inventory.get(itemId).quantity;
        Integer reservedQuantity = reservations.get(reservationId).quantity();
        LocalDateTime reservedAt = reservations.get(reservationId).reservedAt;

        Integer reservedByOthers =  reservations.entrySet().stream()
                .filter(e -> e.getKey() != reservationId && e.getValue().itemId().equals(itemId))
                .filter(e -> e.getValue().reservedAt.isBefore(reservedAt))
                .map(e -> e.getValue().quantity)
                .reduce(0, (a, b) -> a + b);

        return (total - reservedByOthers) >= reservedQuantity ;
    }

    public Integer getReservedByOthers(Integer reservationId) {
        if(!reservations.containsKey(reservationId)) {
            throw new IllegalArgumentException(String.format("reservation: %d is not found", reservationId));
        }

        Integer itemId = reservations.get(reservationId).itemId();
        return getReservedByOthers(reservationId, itemId);
    }

    public Integer getReservedByOthersPrior(Integer reservationId) {
        if(!reservations.containsKey(reservationId)) {
            throw new IllegalArgumentException(String.format("reservation: %d is not found", reservationId));
        }

        Integer itemId = reservations.get(reservationId).itemId();
        return getReservedByOthersPrior(reservationId, itemId);
    }

    public Integer getReservedByOthers(Integer reservationId, Integer itemId) {
        if(!reservations.containsKey(reservationId)) {
            throw new IllegalArgumentException(String.format("reservation: %d is not found", reservationId));
        }
        return reservations.entrySet().stream()
                .filter(e -> e.getKey() != reservationId && e.getValue().itemId().equals(itemId))
                .map(e -> e.getValue().quantity)
                .reduce(0, (a, b) -> a + b);
    }

    public Integer getReservedByOthersPrior(Integer reservationId, Integer itemId) {
        if(!reservations.containsKey(reservationId)) {
            throw new IllegalArgumentException(String.format("reservation: %d is not found", reservationId));
        }
        LocalDateTime reservedAt = reservations.get(reservationId).reservedAt;
        return reservations.entrySet().stream()
                .filter(e -> e.getKey() != reservationId && e.getValue().itemId().equals(itemId))
                .filter(e -> e.getValue().reservedAt.isBefore(reservedAt))
                .map(e -> e.getValue().quantity)
                .reduce(0, (a, b) -> a + b);
    }

    public static void main(String[] args) {
        MarketplaceInventoryReservation marketplaceInventoryReservation = new MarketplaceInventoryReservation();
        SecureRandom random = new SecureRandom();

        IntStream.range(0, 100).forEach(i -> {
            marketplaceInventoryReservation.addInventory(random.nextInt(30), random.nextInt(25));
        });

        marketplaceInventoryReservation.reserve(1, 10, 1);
        marketplaceInventoryReservation.reserve(1, 10, 2);
        marketplaceInventoryReservation.reserve(1, 10, 3);
        marketplaceInventoryReservation.reserve(1, 10, 4);
        marketplaceInventoryReservation.reserve(1, 10, 5);
        marketplaceInventoryReservation.reserve(1, 10, 6);
        marketplaceInventoryReservation.reserve(1, 10, 7);

        System.out.println(String.format("ReservedByOthers: %d", marketplaceInventoryReservation.getReservedByOthers(1)));
        System.out.println(String.format("ReservedByOthers: %d", marketplaceInventoryReservation.getReservedByOthers(3)));

        System.out.println(String.format("ReservedByOthersPrior: %d", marketplaceInventoryReservation.getReservedByOthersPrior(1)));
        System.out.println(String.format("ReservedByOthersPrior: %d", marketplaceInventoryReservation.getReservedByOthersPrior(3)));
//        marketplaceInventoryReservation.getInventory().entrySet().stream().forEach(e -> {
//            System.out.println(String.format("item id: %d, quantity: %d, state: %d", e.getValue().id, e.getValue().quantity, e.getValue().state));
//        });
    }
}
