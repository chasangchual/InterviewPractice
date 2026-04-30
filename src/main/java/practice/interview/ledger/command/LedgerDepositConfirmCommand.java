package practice.interview.ledger.command;

import practice.interview.ledger.LedgerEventType;
import practice.interview.ledger.transaction.UserTransaction;

import java.math.BigDecimal;

public class LedgerDepositConfirmCommand extends LedgerCommand {
    private BigDecimal amount;

    public LedgerDepositConfirmCommand(
            UserTransaction userTransaction,
            BigDecimal amount) {
        super(userTransaction, LedgerEventType.DEPOSIT_CONFIRMED);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
}
