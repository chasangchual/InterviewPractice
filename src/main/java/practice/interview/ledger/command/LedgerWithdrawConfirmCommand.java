package practice.interview.ledger.command;

import practice.interview.ledger.LedgerCommandType;
import practice.interview.ledger.transaction.UserTransaction;

import java.math.BigDecimal;

public class LedgerWithdrawConfirmCommand extends LedgerCommand {
    private final BigDecimal amount;
    public LedgerWithdrawConfirmCommand(
            UserTransaction userTransaction,
            BigDecimal amount) {
        super(userTransaction, LedgerCommandType.WITHDRAW_CONFIRMED);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
