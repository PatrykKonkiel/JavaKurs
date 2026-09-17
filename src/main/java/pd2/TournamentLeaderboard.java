package pd2;

import java.util.Arrays;
import java.util.Scanner;

public class TournamentLeaderboard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj liczbę graczy (2-10): ");
        int n = sc.nextInt();
        sc.nextLine();
        Player[] players;

        if (n >= 2 && n <= 10) {
            players = new Player[n];
        } else {
            throw new IllegalArgumentException("Podana liczba graczy wykracza poza zakres 2-10");
        }

        for (int i = 0; i < n; i++) {
            System.out.println("Podaj imię gracza nr  " + (i + 1) + ":");
            String name = sc.nextLine();

            int[] score = new int[3];
            for (int j = 0; j < 3; j++) {
                System.out.println("Podaj wynik nr " + (j + 1) + ":");
                score[j] = sc.nextInt();
            }
            sc.nextLine();
            players[i] = new Player(name, score);
        }

        Arrays.sort(players);

        int highestScore = players[0].max();
        for (int i = 0; i < n; i++) {
            if (players[i].max() > highestScore) {
                highestScore = players[i].max();
            }
        }

        int highestScoreCount = 0;
        for (int i = 0; i < n; i++) {
            if (players[i].max() == highestScore) {
                highestScoreCount++;
            }
        }

        System.out.println("Leaderboard: ");
        for (int i = 0; i < n; i++) {
            String star = "";
            if (players[i].max() == highestScore && highestScoreCount == 1) {
                star = " *";
            }
            System.out.print("Miejsce nr: " + (i + 1) + " ");
            players[i].printInfo();
            System.out.println(star);
        }
        System.out.println("* - gracz z najwyższym pojedynczym wynikiem");
    }
}
