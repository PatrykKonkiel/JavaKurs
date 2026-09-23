package pd3;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Transaction {
    private final BigDecimal amount;
    private final String paymentProcessor;
    private PaymentStatus status;

    public static Transaction ofComplete(BigDecimal amount, String paymentProcessor) {
        return new Transaction(amount, paymentProcessor, PaymentStatus.COMPLETED);
    }

    public static Transaction ofRefund(BigDecimal amount, String paymentProcessor) {
        return new Transaction(amount, paymentProcessor, PaymentStatus.REFUNDED);
    }

    @Override
    public String toString() {
        return "Tranzakcja: " +
                " Kwota: " + amount +
                " Procesor Płatności: '" + paymentProcessor + '\'' +
                " Status: " + status;

    }
}
