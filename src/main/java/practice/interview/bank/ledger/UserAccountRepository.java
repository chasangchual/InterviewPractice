package practice.interview.bank.ledger;

import java.util.*;

import practice.interview.bank.ledger.UserAccount;

public class UserAccountRepository {

    private static UserAccountRepository instance = null ;

    public static UserAccountRepository getInstance() {
        if (instance == null) {
            instance = new UserAccountRepository();
        }
        return instance;
    }

    private UserAccountRepository() {

    }
    Map<UUID, UserAccount> userAccounts = new HashMap<>();
    public static final Comparator<UserAccount> compareByCreatedAt = Comparator.comparing(UserAccount::getCreatedAt);
    public static final Comparator<UserAccount> compareByBalance = Comparator.comparing(UserAccount::getBalance);

    public void create(String name) {
        userAccounts.computeIfAbsent(UUID.randomUUID(), k -> new UserAccount(k, name));
    }

    public UserAccount find(UUID userId) {
        return userAccounts.get(userId);
    }

    public List<UserAccount> find(String name) {
        return userAccounts.values().stream()
                .filter(userAccount -> userAccount.getName().equals(name)).toList();
    }
}
