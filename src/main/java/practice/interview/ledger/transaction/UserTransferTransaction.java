package practice.interview.ledger.transaction;

import practice.interview.ledger.State;
import practice.interview.ledger.UserAccount;
import practice.interview.ledger.UserAccountTransactionType;
import practice.interview.ledger.command.*;

import java.math.BigDecimal;

public class UserTransferTransaction extends UserTransaction {
    private BigDecimal amount;
    private UserAccount fromUserAccount;
    private UserAccount toUserAccount;

    public UserTransferTransaction(UserAccount fromUserAccount, UserAccount toUserAccount, BigDecimal amount) {
        super(fromUserAccount, UserAccountTransactionType.TRANSFER);
        this.amount = amount;

        this.fromUserAccount = fromUserAccount;
        this.toUserAccount = toUserAccount;
    }

    public void pushCommand(LedgerTransferOutRequestCommand command) {
        this.ledgerCommands.add(command);
        this.state = State.PROCESSING;

        pushCommand(new LedgerTransferOutConfirmCommand(command.getUserTransaction()), command.getAmount());
    }

    public void pushCommand(LedgerTransferOutConfirmCommand command, BigDecimal amount) {
        this.ledgerCommands.add(command);
        this.state = State.PROCESSING;
        fromUserAccount.decreaseBalance(amount);
        pushCommand(new LedgerTransferInRequestCommand(command.getUserTransaction(), amount));
    }

    public void pushCommand(LedgerTransferInRequestCommand command) {
        this.ledgerCommands.add(command);
        this.state = State.PROCESSING;

        pushCommand(new LedgerTransferInConfirmCommand(command.getUserTransaction(), command.getAmount()));
    }

    public void pushCommand(LedgerTransferInConfirmCommand command) {
        this.ledgerCommands.add(command);
        this.state = State.COMPLETE;
        toUserAccount.increaseBalance(amount);
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
}
