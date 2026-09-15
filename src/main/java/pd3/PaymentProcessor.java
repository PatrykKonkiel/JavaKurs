package pd3;

import java.math.BigDecimal;

public interface PaymentProcessor {

    default Transaction processPayment(BigDecimal amount) {
        System.out.println("Płatność na kwotę: " + amount + " zł");
        System.out.println("Tranzakcja zakończona powodzeniem");
        System.out.println("Opłata serwisowa wynosi: " + getTransactionFee(amount) + " zł");
        System.out.println();
        return Transaction.ofComplete(amount, getName());
    }

    default Transaction refund(BigDecimal amount) {
        System.out.println("Zwrot płatności o kwocie: " + amount);
        System.out.println();
        return Transaction.ofRefund(amount, getName());
    }

    BigDecimal getTransactionFee(BigDecimal amount);

    String getName();
}
