package practice.interview.ledger.transaction;

import practice.interview.ledger.State;
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
    protected State state ;
    protected LocalDateTime createdAt;

    public UserTransaction(UserAccount userAccount, UserAccountTransactionType type) {
        this.transactionId = UUID.randomUUID();
        this.userAccount = userAccount;
        this.type = type;
        this.ledgerCommands = new PriorityQueue<>();
        this.state = State.CREATED;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public void pushCommand(LedgerCommand command) {
        this.ledgerCommands.add(command);
    }

    @Override
    public int compareTo(UserTransaction o) {
        return createdAt.compareTo(o.createdAt);
    }
}
