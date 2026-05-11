package practice.interview.bank.ledger;

import org.junit.Test;

public class UserAccountRepositoryTest {
    @Test
    public void create() {
        UserAccountRepository repository = new UserAccountRepository();
        repository.create("user 1");
    }
}