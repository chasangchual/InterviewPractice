package practice.interview.ledger;

import java.math.BigDecimal;
import java.util.*;

public class UserAccountRepository {
    private static final Comparator<UserAccount> BY_BALANCE = Comparator.comparing(UserAccount::getBalance);
    private static final Comparator<UserAccount> BY_CREATED_AT  = Comparator.comparing(UserAccount::getCreatedAt);
    private static final Comparator<UserAccount> BY_BALANCE_AND_CREATED_AT = Comparator.comparing(UserAccount::getBalance)
            .thenComparing(UserAccount::getCreatedAt);

    private static UserAccountRepository instance = null ;
    private Map<Integer, UserAccount> userAccounts = null ;
    public static UserAccountRepository getInstance() {
        if(Objects.isNull(instance)) {
            instance = new UserAccountRepository();
        }
        return instance;
    }

    private UserAccountRepository() {
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

    public List<UserAccount> findAll() {
        return userAccounts.entrySet().stream().map(e -> e.getValue()).sorted(BY_CREATED_AT).toList();
    }

    public Boolean exist(Integer accountId) {
        return userAccounts.containsKey(accountId);
    }
}
