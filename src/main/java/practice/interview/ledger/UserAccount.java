package practice.interview.ledger;

import java.math.BigDecimal;
import java.util.Objects;

public class UserAccount {
    protected Integer accountId;
    protected BigDecimal balance;

    public UserAccount(Integer accountId) {
        this.balance = BigDecimal.ZERO;
    }

    public UserAccount(Integer accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal increaseBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        return this.balance;
    }

    public BigDecimal decreaseBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
        return this.balance;
    }
}
