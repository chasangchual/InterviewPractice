package practice.interview.ledger.command;

import practice.interview.ledger.LedgerCommandType;
import practice.interview.ledger.transaction.UserTransaction;

import java.math.BigDecimal;

public class LedgerTransferInConfirmCommand extends LedgerCommand {
    public LedgerTransferInConfirmCommand(
            UserTransaction userTransaction,
            BigDecimal amount) {
        super(userTransaction, LedgerCommandType.DEPOSIT_CONFIRMED);
    }
}
