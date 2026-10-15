package pd7.password;

import pd7.exception.WeakPasswordException;
import pd7.user.UserValidator;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PasswordValidator {

    public static void validate(String password) {
    Set<String> errors = Stream.of(
                    validatePasswordByDigit(password),
                    validatePasswordByUppercase(password),
                    validatePasswordByLenght(password),
                    validatePasswordBySpecialCharacter(password)
            )
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet());
        if (!errors.isEmpty()) {
        throw new WeakPasswordException(passwordScore(password), String.join(", ", errors));
    }
}
    private static PasswordStrenght passwordScore(String password) {
        return PasswordStrenght.fromScore(passwordScoreCount(password));
    }

    private static int passwordScoreCount(String password) {
        return (int) Stream.of(
                        UserValidator.validatePasswordByDigit(password),
                        UserValidator.validatePasswordByUppercase(password),
                        UserValidator.validatePasswordByLenght(password),
                        UserValidator.validatePasswordBySpecialCharacter(password)
                ).filter(Optional::isEmpty)
                .count();
    }
    public static Optional<String> validatePasswordByLenght(String password) {
        return Optional.of(password)
                .filter(value -> value.length() >= 8)
                .isPresent()
                ? Optional.empty()
                : Optional.of("The password need to contain at least 8 characters");
    }

    public static Optional<String> validatePasswordByUppercase(String password) {
        return Optional.of(password)
                .filter(value -> value.matches(".*[A-Z].*"))
                .isPresent()
                ? Optional.empty()
                : Optional.of("The password need to contain an uppercase letter");
    }

    public static Optional<String> validatePasswordByDigit(String password) {
        return Optional.of(password)
                .filter(value -> value.matches(".*\\d.*"))
                .isPresent()
                ? Optional.empty()
                : Optional.of("The password need to contain a digit");
    }

    public static Optional<String> validatePasswordBySpecialCharacter(String password) {
        return Optional.of(password)
                .filter(value -> value.matches("(?s).*[^a-zA-Z0-9\\s].*"))
                .isPresent()
                ? Optional.empty()
                : Optional.of("The password need to contain special character");
    }
}
