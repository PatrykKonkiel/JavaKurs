package pd7.service;

import pd7.password.PasswordValidator;
import pd7.user.User;
import pd7.user.UserValidator;
import pd7.exception.DuplicateEmailException;

import java.util.*;

public class UserRegistrationService {
    private final Map<String, User> users = new HashMap<>();

    public void registerUser(String name, String email, String password) {
        UserValidator.validate(name, email);
        PasswordValidator.validate(password);
        String normalizedEmail = email.trim().toLowerCase();
        if (users.containsKey(normalizedEmail)) {
            throw new DuplicateEmailException(normalizedEmail);
        }
        String hashedPassword = "hashed_" + password;
        User user = new User(name, normalizedEmail, hashedPassword);
        users.put(normalizedEmail, user);
    }

    public void printDatabase() {
        System.out.println("Database: ");
        if (users.isEmpty()) {
            System.out.println("There are no users in database");
        } else {
            users.values().stream().map(User::toString).forEach(System.out::println);
        }
        System.out.println("Total users in database: " + users.size());
    }
}
