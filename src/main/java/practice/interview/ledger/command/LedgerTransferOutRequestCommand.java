package practice.interview.ledger.command;

import practice.interview.ledger.LedgerEventType;
import practice.interview.ledger.transaction.UserTransaction;

import java.math.BigDecimal;

public class LedgerTransferOutRequestCommand extends LedgerCommand {
    private final BigDecimal amout;

    public LedgerTransferOutRequestCommand(
            UserTransaction userTransaction,
            BigDecimal amout) {
        super(userTransaction, LedgerEventType.TRANSFER_OUT_REQUESTED);
        this.amout = amout;
    }
}
