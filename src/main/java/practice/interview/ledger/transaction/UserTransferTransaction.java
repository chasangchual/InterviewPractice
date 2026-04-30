package practice.interview.ledger.transaction;

import practice.interview.ledger.UserAccount;
import practice.interview.ledger.UserAccountTransactionType;

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

    public BigDecimal getAmount() {
        return this.amount;
    }
}
