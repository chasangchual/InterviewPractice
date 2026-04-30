package practice.interview.ledger.transaction;

import practice.interview.ledger.State;
import practice.interview.ledger.UserAccount;
import practice.interview.ledger.UserAccountTransactionType;
import practice.interview.ledger.command.LedgerCommand;
import practice.interview.ledger.command.LedgerDepositConfirmCommand;
import practice.interview.ledger.command.LedgerDepositRequestCommand;

import java.math.BigDecimal;

public class UserDepositTransaction extends UserTransaction {
    private BigDecimal amount;

    public UserDepositTransaction(UserAccount userAccount, BigDecimal amount) {
        super(userAccount, UserAccountTransactionType.DEPOSIT);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void pushCommand(LedgerDepositRequestCommand command) {
        this.ledgerCommands.add(command);
        this.state = State.PROCESSING;

        pushCommand(new LedgerDepositConfirmCommand(command.getUserTransaction(), command.getAmount()));
    }

    public void pushCommand(LedgerDepositConfirmCommand command) {
        this.ledgerCommands.add(command);
        this.state = State.COMPLETE;
        // increase user balance
        userAccount.increaseBalance(amount);
    }
}
