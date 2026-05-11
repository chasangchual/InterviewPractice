package practice.interview.bank.ledger;

import practice.interview.bank.ledger.command.DepositCommand;
import practice.interview.bank.ledger.command.LedgerCommand;

import java.math.BigDecimal;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

public class UserAccount {
    UUID id;
    String name;
    BigDecimal balance;
    LocalDateTime createdAt;
    List<LedgerCommand> transactions = new ArrayList<>();

    public UserAccount(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.balance = BigDecimal.ZERO;
        this.createdAt = LocalDateTime.now();
    }

    public UserAccount(String name) {
        this(UUID.randomUUID(), name);
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void processCommand(LedgerCommand command) {
        transactions.add(command);

        if(command instanceof DepositCommand) {
            this.balance.add(((DepositCommand) command).getAmount());
        }
    }
}
