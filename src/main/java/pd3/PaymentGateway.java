package pd3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaymentGateway {
    private final List<PaymentProcessor> processors;
    private final List<Transaction> paymentHistory;

    public PaymentGateway(List<PaymentProcessor> processors) {
        if (processors == null || processors.isEmpty()) {
            throw new IllegalArgumentException("Lista procesorów płatności nie może być pusta");
        }
        this.processors = processors;
        this.paymentHistory = new ArrayList<>();
    }

    public PaymentProcessor findBestProcessor(BigDecimal amount) {
        PaymentProcessor bestProcessor = processors.get(0);
        BigDecimal bestFee = bestProcessor.getTransactionFee(amount);
        for (PaymentProcessor processor : processors) {
            BigDecimal fee = processor.getTransactionFee(amount);
            if (fee.compareTo(bestFee) < 0) {
                bestProcessor = processor;
                bestFee = fee;
            }
        }
        return bestProcessor;
    }

    public void processPayment(BigDecimal amount, PaymentProcessor processor, BigDecimal fee) {

        amountValidator(amount, fee);

        System.out.println("Wybrany procesor płatności to " + processor.getName());

        paymentHistory.add(processor.processPayment(amount));


    }

    public void refund(BigDecimal amount, PaymentProcessor processor) {
        System.out.println("Wybrany procesor płatności to " + processor.getName());
        paymentHistory.add(processor.refund(amount));

    }

    public void showHistory() {
        System.out.println("Historia tranzakcji: ");
        for (Transaction transaction : paymentHistory) {
            System.out.println(transaction);
        }
    }

    public BigDecimal calculateTotalAmount(BigDecimal amount, BigDecimal fee) {
        return amount.add(fee);
    }

    public void amountValidator(BigDecimal amount, BigDecimal fee) {
        if (calculateTotalAmount(amount, fee).compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Kwota nie może być na minusie lub wynosić 0");
        }
    }

    public void balanceValidator(BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Kwota przelewu przekracza saldo konta");
        }
    }


}
