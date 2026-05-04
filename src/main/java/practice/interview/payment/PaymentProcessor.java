package practice.interview.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Payment authorization / card transaction processor
 */
public class PaymentProcessor {
    enum State {
        REQUESTED,
        PROCESSED,
        REJECTED,
        INVALID
    }

    class Payment {
        private final UUID id;
        private final Integer accountId;
        private final Integer merchantId;
        private final BigDecimal amount;
        private final LocalDateTime timestamp;
        private State state;
        private final LocalDateTime createdAt;

        public Payment(Integer accountId, Integer merchantId, BigDecimal amount, LocalDateTime timestamp, State state) {
            this.id = UUID.randomUUID();
            this.accountId = accountId;
            this.merchantId = merchantId;
            this.amount = amount;
            this.timestamp = timestamp;
            this.state = state;
            this.createdAt = LocalDateTime.now();
        }

        public UUID getId() {
            return this.id;
        }

        public Integer getAccountId() {
            return this.accountId;
        }

        public Integer getMerchantId() {
            return this.merchantId;
        }

        public BigDecimal getAmount() {
            return this.amount;
        }

        public State getState() {
            return this.state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public LocalDateTime getTimestamp() {
            return this.timestamp;
        }
    };

    private static Comparator<Payment> comppareByTimestamp = Comparator.comparing(Payment::getTimestamp);

    Map<UUID, Payment> payments = new HashMap<>();

    Map<Integer, List<UUID>> userPayments = new HashMap<>();
    Map<Integer, List<UUID>> merchantPayments = new HashMap<>();

    public Boolean authorize(Integer accountId, Integer merchantId, BigDecimal amount, LocalDateTime timestamp) {
        Payment payment = new Payment(accountId, merchantId, amount, timestamp, State.REQUESTED);

        if(isGoodToGo(payment)) {
            add(payment);
            return true;
        }
        return false;
    }

    public Boolean add(Payment payment) {
        try {
            if(payments.containsKey(payment.id)) {
                payment.setState(State.INVALID);
                throw new IllegalArgumentException("Already processed payment");
            }

            if(Boolean.FALSE.equals(payment.getState().equals(State.REQUESTED))) {
                payment.setState(State.INVALID);
                throw new IllegalArgumentException("Invalid state to process");
            }

            payments.put(payment.getId(), payment);
            Objects.requireNonNull(userPayments.putIfAbsent(payment.getAccountId(), new ArrayList<>())).add(payment.getId());
            Objects.requireNonNull(merchantPayments.putIfAbsent(payment.getAccountId(), new ArrayList<>())).add(payment.getId());

            //userPayments.put(payment.getAccountId(), )

        payment.setState(State.PROCESSED);
        } catch (Exception e) {
            return Boolean.FALSE;
        } finally {
            // delete the payment is in REQUESTED state
        }
        return Boolean.FALSE;
    }

    private Boolean isGoodToGo(Payment payment) {
        // get the payments of the user happened before and sort by timestam
        final List<UUID> paymentsForUser = userPayments.get(payment.accountId);
        if(Objects.nonNull(paymentsForUser)) {
            var found = payments.entrySet().stream().filter(e -> paymentsForUser.contains(e.getKey()))
                    .filter(e -> !e.getValue().timestamp.isAfter(payment.getTimestamp()) &&  e.getValue().getTimestamp().plusSeconds(10).isAfter(payment.getTimestamp()))
                    .map(Map.Entry::getValue).toList();
            if(!found.isEmpty() &&
                    found.get(found.size() -1).getMerchantId().equals(payment.getAccountId()) &&
                    found.get(found.size() -1).getAmount().equals(payment.getAmount())) {
                return  false;
            }
        }
        return false;
    }
}
