package practice.interview.ledger;

import practice.interview.ledger.transaction.*;

import java.math.BigDecimal;

public class LedgerService {
    UserAccountRepository userAccountRepository = UserAccountRepository.getInstance();
    UserTransactionRepository userTransactionRepository = UserTransactionRepository.getInstance();

    public Boolean createUsrAccount(Integer accountId) {
        return  userAccountRepository.add(accountId).isPresent();
    }

    public UserAccount findUsrAccount(Integer accountId) {
        return  userAccountRepository.find(accountId).orElseThrow(() -> new IllegalMonitorStateException(""));
    }

    public Boolean deposit(Integer accountId, Double amount) {
        UserAccount userAccount = getUserAccount(accountId);
        UserTransaction userTransaction = new UserDepositTransaction(userAccount, BigDecimal.valueOf(amount));
        userTransactionRepository.triggerTransaction(userAccount, userTransaction);
        return false;

    }
    public Boolean withdraw(Integer accountId, Double amount) {
        UserAccount userAccount = getUserAccount(accountId);
        UserTransaction userTransaction = new UserWithDrawTransaction(userAccount, BigDecimal.valueOf(amount));
        userTransactionRepository.triggerTransaction(userAccount, userTransaction);
        return false;
    }
    public Boolean transfer(Integer fromAccountId, Integer toAccountId, Double  amount) {
        UserAccount fromUserAccount = getUserAccount(fromAccountId);
        UserAccount toUserAccount = getUserAccount(toAccountId);
        UserTransaction userTransaction = new UserTransferTransaction(fromUserAccount, toUserAccount, BigDecimal.valueOf(amount));

        userTransactionRepository.triggerTransaction(fromUserAccount, userTransaction);
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
        return userAccountRepository.find(accountId).orElseThrow(
                () -> new RuntimeException(String.format("User account: %d is not found", accountId))
        );
    }

    public static void main(String[] args) {
        LedgerService ledgerService = new LedgerService();
        ledgerService.createUsrAccount(1);
        ledgerService.createUsrAccount(2);

        ledgerService.deposit(1, 10d);
        UserAccount userAccount1 = ledgerService.findUsrAccount(1);
        System.out.println(userAccount1.getBalance());

        ledgerService.deposit(1, 10d);
        System.out.println(userAccount1.getBalance());

        ledgerService.deposit(2, 30d);
        ledgerService.deposit(2, 30d);
        UserAccount userAccount2 = ledgerService.findUsrAccount(2);
        System.out.println(userAccount2.getBalance());

        ledgerService.withdraw(1, 5d);
        ledgerService.withdraw(2, 15d);
        System.out.println(userAccount1.getBalance());
        System.out.println(userAccount2.getBalance());

        ledgerService.transfer(2, 1, 10d);
        System.out.println(userAccount1.getBalance());
        System.out.println(userAccount2.getBalance());
    }
}
