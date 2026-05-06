package practice.interview.ledger.command;

import practice.interview.ledger.LedgerCommandType;
import practice.interview.ledger.transaction.UserTransaction;

public class LedgerTransferOutConfirmCommand extends LedgerCommand {
    public LedgerTransferOutConfirmCommand(
            UserTransaction userTransaction) {
        super(userTransaction, LedgerCommandType.TRANSFER_OUT_CONFIRMED);
    }
}
