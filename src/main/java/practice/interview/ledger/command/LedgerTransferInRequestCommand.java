package practice.interview.ledger.command;

import practice.interview.ledger.LedgerEventType;
import practice.interview.ledger.transaction.UserTransaction;

import java.math.BigDecimal;

public class LedgerTransferInRequestCommand extends LedgerCommand {
    private final BigDecimal amout;

    public LedgerTransferInRequestCommand(
            UserTransaction userTransaction,
            BigDecimal amount) {
        super(userTransaction, LedgerEventType.DEPOSIT_CONFIRMED);
        this.amout = amount;
    }
}
