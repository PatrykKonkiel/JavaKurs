package pd3;

import java.math.BigDecimal;

public class CryptoProcessor implements PaymentProcessor {

    private static final BigDecimal PROCESS_FEE = BigDecimal.valueOf(0.03);

    @Override
    public BigDecimal getTransactionFee(BigDecimal amount) {
        return amount.multiply(PROCESS_FEE);
    }

    @Override
    public String getName() {
        return "Crypto";
    }
}
