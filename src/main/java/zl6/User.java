package zl6;

public class User {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Imie: " + name;
    }
}
