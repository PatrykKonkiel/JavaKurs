package pd3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PaymentProcessor> processors = new ArrayList<>();
        processors.add(new CreditCardProcessor());
        processors.add(new CryptoProcessor());
        processors.add(new PayPalProcessor());
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
                    balance = balance.subtract(gateway.totalAmount(amount));
                    gateway.balanceValidator(balance);
                    gateway.processPayment(amount);

                }
                case 1 -> {

                    System.out.println("Proszę podać kwotę do zwrotu");
                    BigDecimal amount = sc.nextBigDecimal();
                    balance = balance.add(amount);
                    if (balance.compareTo(initialBalance) >= 0) {
                        throw new IllegalArgumentException("Nie można wykonać takiego zwrotu pieniędzy");
                    }
                    gateway.refund(amount);
                }
                case 2 -> gateway.showHistory();


                default -> System.out.println("Nieprawidłowa operacja");
            }
        } while (operation != 2);
        System.out.println("Saldo wynosi: " + balance);

    }
}
