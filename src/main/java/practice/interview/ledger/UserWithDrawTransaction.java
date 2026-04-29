package practice.interview.ledger;

import java.math.BigDecimal;
import java.util.PriorityQueue;
import java.util.UUID;

public class UserWithDrawTransaction extends UserTransaction {
    private BigDecimal amount;

    public UserWithDrawTransaction(UserAccount userAccount, BigDecimal amount) {
        super(userAccount, UserAccountTransactionType.WITHDRAW);
        this.amount = amount;
    }
}
