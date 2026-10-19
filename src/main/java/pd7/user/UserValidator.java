package pd7.user;

import pd7.exception.ValidationException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserValidator {
    public static void validate(String name, String email) {
        Set<String> errors = Stream.of(
                        validateName(name),
                        validateEmail(email)
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        if (!errors.isEmpty()) {
            throw new ValidationException("User", String.join(", ", errors));
        }
    }

    public static Optional<String> validateName(String name) {
        return Optional.of(name)
                .map(String::trim)
                .filter(value -> value.length() >= 2)
                .filter(value -> value.length() <= 100)
                .filter(value -> value.matches("[\\p{L} -]+"))
                .isPresent()
                ? Optional.empty()
                : Optional.of("Name must contain 2-100 characters and cannot have special signs");
    }

    public static Optional<String> validateEmail(String email) {
        return Optional.of(email)
                .map(String::trim)
                .filter(value -> value.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
                .isPresent()
                ? Optional.empty()
                : Optional.of("Invalid email format");
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
