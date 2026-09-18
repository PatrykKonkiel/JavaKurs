package pd3;

import java.math.BigDecimal;

public class CreditCardProcessor implements PaymentProcessor {

    private static final BigDecimal PROCESS_FEE_PERCENTAGE = BigDecimal.valueOf(0.04);

    @Override
    public BigDecimal getTransactionFee(BigDecimal amount) {
        return amount.multiply(PROCESS_FEE_PERCENTAGE);
    }

    @Override
    public String getName() {
        return "Credit Card";
    }
}
