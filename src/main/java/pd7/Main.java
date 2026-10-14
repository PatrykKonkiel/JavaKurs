package pd7;

import pd7.exception.DuplicateEmailException;
import pd7.exception.RegistrationException;
import pd7.exception.ValidationException;
import pd7.exception.WeakPasswordException;
import pd7.service.UserRegistrationService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserRegistrationService service = new UserRegistrationService();

        boolean running = true;
        while (running) {
            System.out.println("Options:");
            System.out.println("1 - Register user");
            System.out.println("2 - Show database");
            System.out.println("3 - Close program");
            System.out.print("Chose your option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    try {
                        System.out.print("First name: ");
                        String name = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Password: ");
                        String password = scanner.nextLine();
                        service.registerUser(name, email, password);
                        System.out.println("Success");
                        service.printDatabase();
                    } catch (ValidationException e) {
                        System.out.println("Validation ERROR");
                        System.out.println("Field: " + e.getField());
                        System.out.println("Detail: " + e.getMessage());
                        System.out.println("Try again");
                    } catch (DuplicateEmailException e) {
                        System.out.println("Email duplicaiton");
                        System.out.println(e.getMessage());
                    } catch (WeakPasswordException e) {
                        System.out.println("Weak password");
                        System.out.println("Detail: " + e.getDetails());
                        System.out.println("Password Score: " + e.getScore());
                    } catch (RegistrationException e) {
                        System.out.println("Registration Error");
                        System.out.println(e.getMessage());
                    }
                }

                case "2" -> service.printDatabase();

                case "3" -> {
                    System.out.println("Closing program");
                    running = false;
                }

                default -> System.out.println("Invalid option");
            }
        }

    }
}
