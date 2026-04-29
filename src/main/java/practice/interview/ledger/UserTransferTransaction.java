package practice.interview.ledger;

import java.math.BigDecimal;
import java.util.PriorityQueue;
import java.util.UUID;

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
}
