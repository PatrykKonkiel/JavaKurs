package pd3;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PaymentProcessor> processors = List.of(
                new CreditCardProcessor(),
                new CryptoProcessor(),
                new PayPalProcessor());
        PaymentGateway gateway = new PaymentGateway(processors);
        Scanner sc = new Scanner(System.in);
        Account account = new Account(BigDecimal.valueOf(10000));
        int operation;
        do {
            System.out.println("Aktualne saldo " + account.getBalance() + " zł");
            System.out.println("Proszę wybrać działanie");
            System.out.println("0-Wykonanie płatności");
            System.out.println("1-Wykonanie zwrotu pieniedzy");
            System.out.println("2-Pokazanie historii i wyjście");
            operation = sc.nextInt();
            switch (operation) {
                case 0 -> {
                    System.out.println("Proszę wprowadzić kwotę do przelania");
                    BigDecimal amount = sc.nextBigDecimal();
                    PaymentProcessor processor = gateway.findBestProcessor(amount);
                    BigDecimal fee = processor.getTransactionFee(amount);
                    BigDecimal totalAmount = gateway.calculateTotalAmount(amount, fee);
                    account.payment(totalAmount);

                    gateway.processPayment(amount, processor, fee);

                }
                case 1 -> {

                    System.out.println("Proszę podać kwotę do zwrotu");
                    BigDecimal amount = sc.nextBigDecimal();
                    PaymentProcessor processor = gateway.findBestProcessor(amount);
                    account.refund(amount);
                    gateway.refund(amount, processor);

                }
                case 2 -> gateway.showHistory();


                default -> System.out.println("Nieprawidłowa operacja");
            }
        } while (operation != 2);
        System.out.println("Saldo wynosi: " + account.getBalance() + " zł");

    }
}
