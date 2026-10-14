package pd7.service;

import pd7.PasswordStrenght;
import pd7.User;
import pd7.exception.DuplicateEmailException;
import pd7.exception.ValidationException;
import pd7.exception.WeakPasswordException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserRegistrationService {
    private final Map<String, User> users = new HashMap<>();


    public void registerUser(String name, String email, String password) {

        validateUser(name, email);

        String trimedEmail = email.trim().toLowerCase();

        if (users.containsKey(trimedEmail)) {
            throw new DuplicateEmailException(trimedEmail);
        }

        validatePassword(password);

        String hashedPassword = "hashed_" + password;

        User user = new User(name, trimedEmail, hashedPassword);

        users.put(trimedEmail, user);
    }

    private void validateUser(String name, String email) {

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


    private Optional<String> validateName(String name) {
        return Optional.of(name)
                .map(String::trim)
                .filter(name1 -> name1.length() >= 2)
                .filter(name1 -> name1.length() <= 100)
                .filter(name1 -> name1.matches("[\\p{L} -]+"))
                .isPresent()
                ? Optional.empty()
                : Optional.of("Name must contain 2-100 characters and cannot have special signs");
    }


    private Optional<String> validateEmail(String email) {
        return Optional.of(email)
                .map(String::trim)
                .filter(email1 -> email1.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
                .isPresent()
                ? Optional.empty()
                : Optional.of("Invalid email format");
    }

    private void validatePassword(String password) {
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

    private PasswordStrenght passwordScore(String password) {

        int score = passwordScoreCount(password);

        if (score <= 2) {
            return PasswordStrenght.WEAK;
        }

        if (score == 3) {
            return PasswordStrenght.OK;
        }

        return PasswordStrenght.STRONG;
    }

    private int passwordScoreCount(String password) {
        return (int) Stream.of(
                        validatePasswordByDigit(password),
                        validatePasswordByUppercase(password),
                        validatePasswordByLenght(password),
                        validatePasswordBySpecialCharacter(password)
                ).filter(Optional::isEmpty)
                .count();
    }

    private Optional<String> validatePasswordByLenght(String password) {
        return Optional.of(password)
                .filter(password1 -> password1.length() >= 8)
                .isPresent()
                ? Optional.empty()
                : Optional.of("The password need to contain at least 8 characters");
    }

    private Optional<String> validatePasswordByUppercase(String password) {
        return Optional.of(password)
                .filter(password1 -> password1.matches(".*[A-Z].*"))
                .isPresent()
                ? Optional.empty()
                : Optional.of("The password need to contain an uppercase letter");
    }

    private Optional<String> validatePasswordByDigit(String password) {
        return Optional.of(password)
                .filter(password1 -> password1.matches(".*\\d.*"))
                .isPresent()
                ? Optional.empty()
                : Optional.of("The password need to contain a digit");
    }

    private Optional<String> validatePasswordBySpecialCharacter(String password) {
        return Optional.of(password)
                .filter(password1 -> password1.matches("(?s).*[^a-zA-Z0-9\\s].*"))
                .isPresent()
                ? Optional.empty()
                : Optional.of("The password need to contain special character");
    }

    public void printDatabase() {
        System.out.println("Database: ");
        if (users.isEmpty()) {
            System.out.println("There are no users in database");
        } else {
            users.values().stream().map(User::toString).forEach(System.out::println);
        }
        System.out.println("Total users in database: "+ users.size());

    }


}
