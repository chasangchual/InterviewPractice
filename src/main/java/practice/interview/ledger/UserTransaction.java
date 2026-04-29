package practice.interview.ledger;

import java.util.PriorityQueue;
import java.util.UUID;

public class UserTransaction {
    private final UUID transactionId;
    private final UserAccount userAccount;
    private final PriorityQueue<LedgerEvent> events;
    private final UserAccountTransactionType type;

    public UserTransaction(UserAccount userAccount, UserAccountTransactionType type) {
        this.transactionId = UUID.randomUUID();
        this.userAccount = userAccount;
        this.type = type;
        this.events = new PriorityQueue<>();
    }
    public void pushEvent(LedgerEvent event) {
        this.events.add(event);
    }
}
