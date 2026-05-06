package practice.interview.ledger.command;

import practice.interview.ledger.LedgerCommandType;
import practice.interview.ledger.transaction.UserTransaction;

import java.math.BigDecimal;

public class LedgerTransferInRequestCommand extends LedgerCommand {
    private final BigDecimal amount;

    public LedgerTransferInRequestCommand(
            UserTransaction userTransaction,
            BigDecimal amount) {
        super(userTransaction, LedgerCommandType.DEPOSIT_CONFIRMED);
        this.amount = amount;
    }
    public BigDecimal getAmount() {
        return amount;
    }
}
