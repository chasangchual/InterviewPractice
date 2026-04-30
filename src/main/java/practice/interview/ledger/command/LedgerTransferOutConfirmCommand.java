package practice.interview.ledger.command;

import practice.interview.ledger.LedgerEventType;
import practice.interview.ledger.transaction.UserTransaction;

public class LedgerTransferOutConfirmCommand extends LedgerCommand {
    public LedgerTransferOutConfirmCommand(
            UserTransaction userTransaction) {
        super(userTransaction, LedgerEventType.TRANSFER_OUT_CONFIRMED);
    }
}
