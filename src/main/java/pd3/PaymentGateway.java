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
        for (PaymentProcessor processor : processors) {
            if (processor.getTransactionFee(amount).compareTo(bestProcessor.getTransactionFee(amount)) < 0) {
                bestProcessor = processor;
            }
        }
        System.out.println("Wybrany procesor płatności to " + bestProcessor.getName());
        return bestProcessor;
    }

    public void processPayment(BigDecimal amount) {
        amountValidator(amount);
        PaymentProcessor processor = findBestProcessor(amount);
        paymentHistory.add(processor.processPayment(amount));


    }

    public void refund(BigDecimal amount) {
        amountValidator(amount);
        PaymentProcessor processor = findBestProcessor(amount);
        paymentHistory.add(processor.refund(amount));

    }

    public void showHistory() {
        System.out.println("Historia tranzakcji: ");
        for (Transaction transaction : paymentHistory) {
            System.out.println(transaction);
        }
    }

    public BigDecimal totalAmount(BigDecimal amount) {
        PaymentProcessor processor = findBestProcessor(amount);
        return processor.getTransactionFee(amount).add(amount);
    }

    public void amountValidator(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Kwota nie może być na minusie lub wynosić 0");
        }
    }

    public void balanceValidator(BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Kwota przelewu przekracza saldo konta");
        }
    }


}
