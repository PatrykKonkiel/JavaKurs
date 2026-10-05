package pd6.match;

import lombok.NoArgsConstructor;
import pd6.participant.Participant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor(staticName = "of")
public class Tournament {

    private final List<Participant> participants = new ArrayList<>();
    private final List<Match> matches = new ArrayList<>();

    public void add(Participant participant) {
        participants.add(participant);
    }

    public void play(Participant one, Participant two, MatchResult result) {
        Match match = Match.played(one, two, result);
        matches.add(match);
        PointApplier.apply(match);
    }

    public List<Participant> standings() {
        return participants.stream().sorted(Comparator.
                comparingInt(Participant::getPoints).reversed()).toList();
    }

    public void printMatches() {
        matches.forEach(match -> System.out.println(match.getOne().getName()
                + " vs " + match.getTwo().getName() + " Result: " + match.getResult()));
    }

    public <T extends Participant> List<T> getParticipantByType(Class<T> type) {
        return participants.stream().filter(type::isInstance).map(type::cast).toList();
    }

    public <T extends Participant> void printParticipantByType(Class<T> type) {
        System.out.println(type.getSimpleName()+":");

        getParticipantByType(type).forEach(participant ->
                System.out.println(participant.getName() + " " + participant.getPoints()));

    }
}
