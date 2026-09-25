package zl6;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OperationResult<User> userResult = OperationResult.success(new User("Janek"));
        OperationResult<Double> score = OperationResult.success(5.5);
        OperationResult<List<String>> names = OperationResult.success(List.of("Janek", "Paweł", "Marcin"));
        OperationResult<Product> productResult = OperationResult.success(new Product("Telewizor"));

        System.out.println(userResult.getValue() + " " + userResult.getMessage());
        System.out.println(score.getValue() + " " + score.getMessage());
        System.out.println(names.getValue() + " " + names.getMessage());
        System.out.println(productResult.getValue() + " " + productResult.getMessage());


//        OperationResult<User> xd = OperationResult.success(new Product("Piwo"));
    }
}
