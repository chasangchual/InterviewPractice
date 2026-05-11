package practice.interview.bank.ledger.command;

import java.math.BigDecimal;

public class DepositCommand extends LedgerCommand {
    private final BigDecimal amount;
    public DepositCommand(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
}
