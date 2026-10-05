package pd6;

import pd6.match.MatchResult;
import pd6.match.Tournament;
import pd6.participant.Participant;
import pd6.participant.Player;
import pd6.participant.Team;

public class Main {
    public static void main(String[] args) {
        Tournament tournament = Tournament.of();
        Participant janek = new Player("Janek");
        Participant marcin = new Player("Marcin");
        Participant legia = new Team("Legia");
        tournament.add(marcin);
        tournament.add(janek);
        tournament.add(legia);

        tournament.play(janek, marcin, MatchResult.FIRST_WIN);
        tournament.play(janek, marcin, MatchResult.FIRST_WIN);
        tournament.play(janek, marcin, MatchResult.FIRST_WIN);
        tournament.play(janek, marcin, MatchResult.SECOND_WIN);
        tournament.play(janek, marcin, MatchResult.SECOND_WIN);
        tournament.play(janek, marcin, MatchResult.DRAW);
        tournament.play(legia, marcin, MatchResult.DRAW);
        tournament.printMatches();
        tournament.printParticipantByType(Player.class);
        tournament.printParticipantByType(Team.class);

        System.out.println("Scoreboard: ");

        tournament.standings().forEach(p -> System.out.println(p.getName() + " " + p.getPoints()));


    }
}
