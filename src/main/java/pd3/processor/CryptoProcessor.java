package pd3.processor;

import java.math.BigDecimal;

public class CryptoProcessor implements PaymentProcessor {

    private static final BigDecimal PROCESS_FEE = BigDecimal.valueOf(11);

    @Override
    public BigDecimal getTransactionFee(BigDecimal amount) {
        return PROCESS_FEE;
    }

    @Override
    public String getName() {
        return "Crypto";
    }
}
