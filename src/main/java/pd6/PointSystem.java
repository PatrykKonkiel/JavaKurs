package pd6;

public class PointSystem<T extends Participant> {
    public void apply(Match match) {
        switch (match.getResult()) {
            case ONE_WIN -> match.getOne().addPoints(2);

            case DRAW -> {
                match.getOne().addPoints(1);
                match.getTwo().addPoints(1);
            }

            case TWO_WIN -> match.getTwo().addPoints(2);
        }
    }
}
