package pd7.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RegistrationException {
    private final String field;

    public ValidationException(String field, String message) {
        super(message);
        this.field = field;
    }
}
