package pd1;

import java.util.Scanner;

public class Macierze {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FunkcjeMacierzy operation = new FunkcjeMacierzy();
        System.out.println("Podaj 9 liczb do utworzenia macierzy");
        int[][] matrix = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        System.out.println("Macierz: ");

        operation.printMatrix(matrix);

        System.out.println("Macierz transponowana:");

        int[][] transpositon = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                transpositon[j][i] = matrix[i][j];
            }
        }
        operation.printMatrix(transpositon);

        System.out.println("Macierz obrócona o 90 stopni w prawo: ");

        int[][] rotation = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotation[j][2 - i] = matrix[i][j];
            }
        }
        operation.printMatrix(rotation);

        for (int i = 0; i < 3; i++) {
            int rowSum = 0;
            int columnSum = 0;
            for (int j = 0; j < 3; j++) {
                rowSum += matrix[i][j];
                columnSum += matrix[j][i];

            }
            System.out.println("Suma wiersza " + (i + 1) + " wynosi: " + rowSum);
            System.out.println("Suma kolumny " + (i + 1) + " wynosi: " + columnSum);
        }

        if (operation.symetricCheck(matrix)) {
            System.out.println("Macierz jest symetryczna");
        } else {
            System.out.println("Macierz nie jest symetryczna");
        }

    }

}
