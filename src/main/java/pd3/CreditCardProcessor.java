package pd3;

import java.math.BigDecimal;

public class CreditCardProcessor implements PaymentProcessor {

    private final BigDecimal PROCESS_FEE = BigDecimal.valueOf(0.01);

    @Override
    public BigDecimal getTransactionFee(BigDecimal amount) {
        return amount.multiply(PROCESS_FEE);
    }

    @Override
    public String getName() {
        return "Credit Card";
    }
}
