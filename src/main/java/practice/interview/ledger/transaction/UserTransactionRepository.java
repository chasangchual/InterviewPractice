package practice.interview.ledger.transaction;

import practice.interview.ledger.UserAccount;
import practice.interview.ledger.command.LedgerDepositRequestCommand;
import practice.interview.ledger.command.LedgerTransferOutConfirmCommand;
import practice.interview.ledger.command.LedgerTransferOutRequestCommand;
import practice.interview.ledger.command.LedgerWithdrawRequestCommand;

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

    public void triggerTransaction(UserAccount userAccount, UserTransaction transaction) {
        userTransactions.putIfAbsent(userAccount, new PriorityQueue<>());

        if (transaction instanceof UserDepositTransaction) {
            var depositTransaction = (UserDepositTransaction) transaction;
            depositTransaction.pushCommand(new LedgerDepositRequestCommand(depositTransaction, depositTransaction.getAmount()));

            var transactions = userTransactions.get(userAccount);
            transactions.
                    add(depositTransaction);

            userTransactions.put(userAccount, transactions);
        } else if(transaction instanceof UserWithDrawTransaction) {
            var withdrawTransaction = (UserWithDrawTransaction) transaction;
            withdrawTransaction.pushCommand(new LedgerWithdrawRequestCommand(withdrawTransaction, withdrawTransaction.getAmount()));

            var transactions = userTransactions.get(userAccount);
            transactions.add(withdrawTransaction);

            userTransactions.put(userAccount, transactions);
        } else if(transaction instanceof UserTransferTransaction) {
            var transferTransaction = (UserTransferTransaction) transaction;
            transferTransaction.pushCommand(new LedgerTransferOutRequestCommand(transferTransaction, transferTransaction.getAmount()));

            var transactions = userTransactions.get(userAccount);
            transactions.add(transferTransaction);

            userTransactions.put(userAccount, transactions);
        } else {
            throw new RuntimeException(String.format("%s unsupported", transaction.getClass().toString()));
        }
    }
}
