package pd7.exception;

import lombok.Getter;

@Getter
public class DuplicateEmailException extends RegistrationException {

    private final String email;

    public DuplicateEmailException(String email) {
        super("This emial is already used: " + email);
        this.email = email;
    }
}
