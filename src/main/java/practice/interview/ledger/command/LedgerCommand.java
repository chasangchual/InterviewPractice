package practice.interview.ledger.command;

import practice.interview.ledger.LedgerCommandType;
import practice.interview.ledger.transaction.UserTransaction;

import java.time.LocalDateTime;
import java.util.UUID;

public class LedgerCommand implements Comparable<LedgerCommand> {
    private final UUID id ;
    private final UserTransaction userTransaction;
    private final LedgerCommandType type;
    private final LocalDateTime createdAt;

    public LedgerCommand(
            UserTransaction userTransaction,
            LedgerCommandType type) {
        this.id = UUID.randomUUID();
        this.userTransaction = userTransaction;
        this.type = type;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public int compareTo(LedgerCommand o) {
        return this.createdAt.compareTo(o.createdAt);
    }

    public UserTransaction getUserTransaction() {
        return this.userTransaction;
    }
}
