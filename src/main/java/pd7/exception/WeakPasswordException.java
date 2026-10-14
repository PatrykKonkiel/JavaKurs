package pd7.exception;

import lombok.Getter;
import pd7.PasswordStrenght;

@Getter
public class WeakPasswordException extends RegistrationException {
    private final PasswordStrenght score;
    private final String details;

    public WeakPasswordException(PasswordStrenght score, String details) {
        super("This password is too weak. Password score: " + score + " " + details);
        this.details = details;
        this.score = score;

    }
}
