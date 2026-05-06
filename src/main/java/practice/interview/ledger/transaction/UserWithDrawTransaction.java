package practice.interview.ledger.transaction;

import practice.interview.ledger.TransactionState;
import practice.interview.ledger.UserAccount;
import practice.interview.ledger.UserAccountTransactionType;
import practice.interview.ledger.command.LedgerWithdrawConfirmCommand;
import practice.interview.ledger.command.LedgerWithdrawRequestCommand;

import java.math.BigDecimal;

public class UserWithDrawTransaction extends UserTransaction {
    private BigDecimal amount;

    public UserWithDrawTransaction(UserAccount userAccount, BigDecimal amount) {
        super(userAccount, UserAccountTransactionType.WITHDRAW);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void pushCommand(LedgerWithdrawRequestCommand command) {
        this.ledgerCommands.add(command);
        this.transactionState = TransactionState.PROCESSING;

        pushCommand(new LedgerWithdrawConfirmCommand(command.getUserTransaction(), command.getAmount()));
    }

    public void pushCommand(LedgerWithdrawConfirmCommand command) {
        this.ledgerCommands.add(command);
        this.transactionState = TransactionState.COMPLETE;
        // increase user balance
        userAccount.decreaseBalance(amount);
    }

}
