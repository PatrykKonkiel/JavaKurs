package zl2;

import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj wage");
        double weight = sc.nextDouble();
        System.out.println("Podaj wzrost");
        double height = sc.nextDouble();
        if (height < 30 || height > 250) {
            System.err.println("Nieprawidłowy wzrost");
        }
        double bmi = weight / ((height / 100.0) * (height / 100.0));
        System.out.println("BMI wynosi : " + String.format("%.2f", bmi));
        if (bmi < 18.5) {
            System.out.println("Niedowaga");
        }
        if (bmi > 18.5 && bmi < 24.9) {
            System.out.println("Normalna waga");
        }
        if (bmi >= 30) {
            System.out.println("Otyłość");
        }


    }
}
