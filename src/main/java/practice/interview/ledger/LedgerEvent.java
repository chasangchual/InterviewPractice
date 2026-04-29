package practice.interview.ledger;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LedgerEvent(
        UserAccount userAccount,
        LedgerEventType type,
        BigDecimal amount,
        LocalDateTime createdAt) implements Comparable<LedgerEvent> {

    @Override
    public int compareTo(LedgerEvent o) {
        return this.createdAt.compareTo(o.createdAt);
    }
}
