package pd7.password;

public enum PasswordStrenght {
    WEAK,
    OK,
    STRONG;

    public static PasswordStrenght fromScore(int score) {
        if (score <= 2) {
            return WEAK;
        }
        if (score == 3) {
            return OK;
        }
        return STRONG;
    }
}