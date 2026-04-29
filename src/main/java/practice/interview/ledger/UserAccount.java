package practice.interview.ledger;

import java.math.BigDecimal;
import java.util.Objects;

public record UserAccount(Integer accountId, BigDecimal balance) {
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
}
