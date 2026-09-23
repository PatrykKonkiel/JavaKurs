package pd3;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Account {
    private BigDecimal balance;
    private BigDecimal initialBalance;

    public Account(BigDecimal balance) {
        if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo nie może być ujemne");
        }
        this.balance = balance;
        this.initialBalance = balance;
    }

    public void payment(BigDecimal amount) {
        validateAmount(amount);
        if (amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException("Kwota przekracza saldo konta");
        }
        balance = balance.subtract(amount);
    }

    public void refund(BigDecimal amount) {
        validateRefund();
        validateAmount(amount);
        balance = balance.add(amount);
    }

    public void validateRefund() {
        if (balance.compareTo(initialBalance) >= 0) {
            throw new IllegalArgumentException("Nie można wykonać takiego zwrotu");
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Kwota musi być większa od zera");
        }
    }
}
