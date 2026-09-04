package zl2;

import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj wage w kilogramach");
        double weight = sc.nextDouble();
        System.out.println("Podaj wzrost w centymetrach");
        double heightInCM = sc.nextDouble();
        if (heightInCM < 30 || heightInCM > 250) {
            throw new IllegalArgumentException("Podano nieprawidłowy wzrost");
        }
        double heightInM = heightInCM / 100;
        double bmi = weight / (heightInM * heightInM);
        System.out.println("BMI wynosi : " + String.format("%.2f", bmi));
        if (bmi <= 18.5) {
            System.out.println("Niedowaga");
        } else if (bmi > 18.5 && bmi <= 25) {
            System.out.println("Normalna waga");
        } else if (bmi > 25 && bmi < 30) {
            System.out.println("Nadwaga");
        } else {
            System.out.println("Otyłość");
        }
    }
}
