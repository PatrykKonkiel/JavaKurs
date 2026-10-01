package pd6;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Match{
    private final Participant one;
    private final Participant two;
    private final MatchResult result;

    public static Match played(Participant one, Participant two, MatchResult result) {
        if (one == two) {
            throw new IllegalArgumentException("Participant cannot play against themselves");
        }
        return new Match(one, two, result);
    }
}
