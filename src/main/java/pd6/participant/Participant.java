package pd6.participant;

import lombok.Getter;

@Getter
public abstract class Participant {
    public final String name;
    public int points;

    public Participant(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
        this.points = 0;
    }

    public void addPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative");
        }
        this.points += points;
    }
}
