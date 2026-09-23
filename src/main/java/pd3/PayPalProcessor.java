package pd3;

import java.math.BigDecimal;

public class PayPalProcessor implements PaymentProcessor {

    private static final BigDecimal PROCESS_FEE = BigDecimal.valueOf(2);
    private static final BigDecimal PROCESS_FEE_PERCENTAGE = BigDecimal.valueOf(0.01);

    @Override
    public BigDecimal getTransactionFee(BigDecimal amount) {
        return amount.multiply(PROCESS_FEE_PERCENTAGE).add(PROCESS_FEE);
    }

    @Override
    public String getName() {
        return "PayPal";
    }
}
