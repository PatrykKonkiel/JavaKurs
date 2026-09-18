package pd3;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        BigDecimal balance = BigDecimal.valueOf(10000);
        BigDecimal initialBalance = balance;
        int operation;
        do {
            System.out.println("Aktualne saldo " + balance + " zł");
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
                    BigDecimal newBalance = balance.subtract(totalAmount);
                    if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                        throw new IllegalArgumentException("Kowta przekracza saldo konta");
                    }

                    gateway.processPayment(amount, processor, fee);
                    balance = newBalance;
                }
                case 1 -> {

                    System.out.println("Proszę podać kwotę do zwrotu");
                    BigDecimal amount = sc.nextBigDecimal();
                    PaymentProcessor processor = gateway.findBestProcessor(amount);
                    BigDecimal newBalance = balance.add(amount);
                    if (balance.compareTo(initialBalance) >= 0) {
                        throw new IllegalArgumentException("Nie można wykonać takiego zwrotu pieniędzy");
                    }
                    gateway.refund(amount, processor);
                    balance = newBalance;
                }
                case 2 -> gateway.showHistory();


                default -> System.out.println("Nieprawidłowa operacja");
            }
        } while (operation != 2);
        System.out.println("Saldo wynosi: " + balance + " zł");

    }
}
