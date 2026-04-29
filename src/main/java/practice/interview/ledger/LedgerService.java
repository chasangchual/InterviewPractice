package practice.interview.ledger;

import java.math.BigDecimal;
import java.util.Map;
import java.util.PriorityQueue;

public class LedgerService {
    UserAccountRepossitory userAccountService = UserAccountRepossitory.getInstance();
    UserTransactionRepository userTransactionRepository = UserTransactionRepository.getInstance();

    public Boolean deposit(Integer accountId, Double amount) {
        UserAccount userAccount = getUserAccount(accountId);
        UserTransaction userTransaction = new UserDepositTransaction(userAccount, BigDecimal.valueOf(amount));
        userTransactionRepository.newTransaction(userAccount, userTransaction);
        return false;

    }
    public Boolean withdraw(Integer accountId, Double amount) {
        UserAccount userAccount = getUserAccount(accountId);
        UserTransaction userTransaction = new UserWithDrawTransaction(userAccount, BigDecimal.valueOf(amount));
        userTransactionRepository.newTransaction(userAccount, userTransaction);
        return false;
    }
    public Boolean transfer(Integer fromAccountId, Integer toAccountId, Double  amount) {
        UserAccount fromUserAccount = getUserAccount(fromAccountId);
        UserAccount toUserAccount = getUserAccount(toAccountId);
        UserTransaction userTransaction = new UserTransferTransaction(fromUserAccount, toUserAccount, BigDecimal.valueOf(amount));

        userTransactionRepository.newTransaction(fromUserAccount, userTransaction);
        return false;

    }
    public Boolean getBalance(Integer accountId) {
        UserAccount userAccount = getUserAccount(accountId);
        return false;

    }
    public Boolean getTransactionHistory(Integer accountId) {
        UserAccount userAccount = getUserAccount(accountId);
        return false;
    }

    private UserAccount getUserAccount(Integer accountId) {
        return userAccountService.find(accountId).orElseThrow(
                () -> new RuntimeException(String.format("User account: %d is not found", accountId))
        );
    }
}
