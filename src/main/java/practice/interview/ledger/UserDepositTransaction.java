package practice.interview.ledger;

import java.math.BigDecimal;
import java.util.PriorityQueue;
import java.util.UUID;

public class UserDepositTransaction extends UserTransaction {
    private BigDecimal amount;

    public UserDepositTransaction(UserAccount userAccount, BigDecimal amount) {
        super(userAccount, UserAccountTransactionType.DEPOSIT);
        this.amount = amount;
    }
}
