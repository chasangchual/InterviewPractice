package practice.interview.ledger.transaction;

import practice.interview.ledger.TransactionState;
import practice.interview.ledger.UserAccount;
import practice.interview.ledger.UserAccountTransactionType;
import practice.interview.ledger.command.LedgerCommand;

import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.UUID;

public class UserTransaction implements Comparable<UserTransaction>{
    protected final UUID transactionId;
    protected final UserAccount userAccount;
    protected final PriorityQueue<LedgerCommand> ledgerCommands;
    protected final UserAccountTransactionType type;
    protected TransactionState transactionState;
    protected LocalDateTime createdAt;

    public UserTransaction(UserAccount userAccount, UserAccountTransactionType type) {
        this.transactionId = UUID.randomUUID();
        this.userAccount = userAccount;
        this.type = type;
        this.ledgerCommands = new PriorityQueue<>();
        this.transactionState = TransactionState.CREATED;
        this.createdAt = LocalDateTime.now();
    }

    public void setState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public TransactionState getState() {
        return this.transactionState;
    }

    public void pushCommand(LedgerCommand command) {
        this.ledgerCommands.add(command);
    }

    @Override
    public int compareTo(UserTransaction o) {
        return createdAt.compareTo(o.createdAt);
    }
}
