package zl3;

import java.util.Scanner;

public class DayCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj numer dnia tygodnia");
        int dayNumber = sc.nextInt();
        String day = switch (dayNumber) {
            case 1 -> "Poniedziałek";
            case 2 -> "Wtorek";
            case 3 -> "Środa";
            case 4 -> "Czwartek";
            case 5 -> "Piątek";
            case 6 -> "Sobota";
            case 7 -> "Niedziela";
            default -> throw new IllegalArgumentException("Podano numer z poza zakresu: " + dayNumber);
        };
        System.out.println("Dzień tygodnia to: " + day);
        if (dayNumber >= 1 && dayNumber <= 5) {
            System.out.println("Jest to dzień pracujący");
            System.out.println("Pierwszy autobus odjedzie o godzinie: 5:30");
        } else {
            System.out.println("Jest to weekend");
            if (dayNumber == 6) {
                System.out.println("Pierwszy autobus odjedzie o godzinie: 7:00");
            } else {
                System.out.println("Pierwszy autobus odjedzie o godzinie: 9:00");
            }
        }
    }
}
