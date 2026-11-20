package pd16;

import pd16.entity.Posts;
import java.io.IOException;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ApiGateway apiGateway = ApiGateway.initialize();
        UserReport userReport = apiGateway.getUserReport(5L);
        System.out.println("User:");
        System.out.println(userReport.getUser());
        System.out.println("User todos:");
        userReport.getTodosList().stream()
                .filter(todos -> !todos.isCompleted())
                .forEach(System.out::println);
        System.out.println("User posts:");
        userReport.getPostsList().stream()
                .sorted(Comparator
                        .comparing(Posts::getId).reversed())
                .limit(3)
                .forEach(System.out::println);
        System.out.println("Current temperature " + userReport.getWeather().getCurrent().getTemperature() + " C");
    }
}