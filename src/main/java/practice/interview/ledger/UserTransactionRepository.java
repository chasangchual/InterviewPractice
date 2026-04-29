package practice.interview.ledger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class UserTransactionRepository {
    private Map<UserAccount, PriorityQueue<UserTransaction>> userTransactions;

    private static UserTransactionRepository instance = null;
    public static UserTransactionRepository getInstance() {
        if(Objects.isNull(instance))
        {
            instance = new UserTransactionRepository();
        }
        return instance;
    }

    private UserTransactionRepository() {
        userTransactions = new HashMap<UserAccount, PriorityQueue<UserTransaction>>();
    }

    public void newTransaction(UserAccount userAccount, UserTransaction userTransaction) {
        var transactions = userTransactions.putIfAbsent(userAccount, new PriorityQueue<>());
        transactions.add(userTransaction);
    }
}
