package pd6;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Tournament tournament = new Tournament(new PointSystem<>());
        Participant janek = new Player("Janek");
        Participant marcin = new Player("Marcin");
        Participant legia = new Team("Legia");
        tournament.add(marcin);
        tournament.add(janek);
        tournament.add(legia);

        tournament.play(janek, marcin, MatchResult.ONE_WIN);
        tournament.play(janek, marcin, MatchResult.ONE_WIN);
        tournament.play(janek, marcin, MatchResult.ONE_WIN);
        tournament.play(janek, marcin, MatchResult.TWO_WIN);
        tournament.play(janek, marcin, MatchResult.TWO_WIN);
        tournament.play(janek, marcin, MatchResult.DRAW);
        tournament.play(legia, marcin, MatchResult.DRAW);
        tournament.printMatches();
        tournament.printParticipantByType(Player.class);
        tournament.printParticipantByType(Team.class);

        System.out.println("Scoreboard: ");

        tournament.standings().forEach(p -> System.out.println(p.getName() + " " + p.getPoints()));


    }
}
