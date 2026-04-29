package practice.interview.ledger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class UserAccountRepossitory {
    private static UserAccountRepossitory instance = null ;
    private Map<Integer, UserAccount> userAccounts = null ;
    public static UserAccountRepossitory getInstance() {
        if(Objects.isNull(instance)) {
            instance = new UserAccountRepossitory();
        }
        return instance;
    }

    private UserAccountRepossitory() {
        this.userAccounts = new HashMap<>();
    }

    public Optional<UserAccount> add(Integer accountId) {
        if(exist(accountId)) {
            System.out.printf("user account: %d already exist%n", accountId);
            return  Optional.empty();
        }

        try {
            var userAccount = userAccounts.computeIfAbsent(accountId, k ->  new UserAccount(accountId, BigDecimal.ZERO));
            return Optional.of(userAccount);
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return  Optional.empty();
    }

    public Optional<UserAccount> find(Integer accountId) {
        return Objects.nonNull(userAccounts.get(accountId)) ? Optional.of(userAccounts.get(accountId))
                : Optional.empty();
    }

    public Boolean exist(Integer accountId) {
        return userAccounts.containsKey(accountId);
    }
}
