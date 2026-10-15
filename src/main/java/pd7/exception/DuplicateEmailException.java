package pd7.exception;

public class DuplicateEmailException extends RegistrationException {
    public DuplicateEmailException(String email) {
        super("This emial is already used: " + email);
    }
}
