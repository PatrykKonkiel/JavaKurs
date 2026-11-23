package pd16;

import com.fasterxml.jackson.databind.ObjectMapper;
import pd16.entity.Posts;
import pd16.gateway.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient httpClient =  HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .version(HttpClient.Version.HTTP_2)
                .build();
        Response response = Response.of(httpClient);
        ApiGateway apiGateway = ApiGateway.of(
                UserGateway.of(objectMapper,response),
                PostsGateway.of(objectMapper,response),
                TodosGateway.of(objectMapper,response),
                WeatherGateway.of(objectMapper,response)
        );
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