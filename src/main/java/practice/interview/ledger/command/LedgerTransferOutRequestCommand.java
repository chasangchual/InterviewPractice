package practice.interview.ledger.command;

import practice.interview.ledger.LedgerCommandType;
import practice.interview.ledger.transaction.UserTransaction;

import java.math.BigDecimal;

public class LedgerTransferOutRequestCommand extends LedgerCommand {
    private final BigDecimal amount;

    public LedgerTransferOutRequestCommand(
            UserTransaction userTransaction,
            BigDecimal amount) {
        super(userTransaction, LedgerCommandType.TRANSFER_OUT_REQUESTED);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
