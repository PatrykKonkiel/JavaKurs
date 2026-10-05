package pd6.match;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PointApplier {
    public static void apply(Match match) {
        switch (match.getResult()) {
            case FIRST_WIN -> match.getOne().addPoints(2);

            case DRAW -> {
                match.getOne().addPoints(1);
                match.getTwo().addPoints(1);
            }

            case SECOND_WIN -> match.getTwo().addPoints(2);
        }
    }
}
