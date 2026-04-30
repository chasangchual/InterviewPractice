package practice.interview.ledger.command;

import practice.interview.ledger.LedgerEventType;
import practice.interview.ledger.transaction.UserTransaction;

import java.math.BigDecimal;

public class LedgerWithdrawRequestCommand extends LedgerCommand {
    private final BigDecimal amount;
    public LedgerWithdrawRequestCommand(
            UserTransaction userTransaction,
            BigDecimal amount) {
        super(userTransaction, LedgerEventType.WITHDRAW_REQUESTED);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
}
