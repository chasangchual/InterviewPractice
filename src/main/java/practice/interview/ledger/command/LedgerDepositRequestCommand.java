package practice.interview.ledger.command;

import practice.interview.ledger.LedgerEventType;
import practice.interview.ledger.transaction.UserTransaction;

import java.math.BigDecimal;

public class LedgerDepositRequestCommand extends LedgerCommand {
    private final BigDecimal amount;
    public LedgerDepositRequestCommand(
            UserTransaction userTransaction,
            BigDecimal amount) {
        super(userTransaction, LedgerEventType.DEPOSIT_REQUESTED);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
}
