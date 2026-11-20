package pd16;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import pd16.entity.Posts;
import pd16.entity.Todos;
import pd16.entity.User;
import pd16.entity.Weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor(staticName = "initialize")
public class ApiGateway {
    ObjectMapper objectMapper = new ObjectMapper();

    public UserReport getUserReport(Long userId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest getRequestUser = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();

        HttpResponse<String> getResponseUser = client.send(getRequestUser, HttpResponse.BodyHandlers.ofString());
        User user = objectMapper.readValue(getResponseUser.body(), User.class);

        HttpRequest getRequestUserTodos = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId + "/todos"))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();
        HttpRequest getRequestUserPosts = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId + "/posts"))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();
        String latitude = user.getAddress().getGeo().getLat();
        String longitude = user.getAddress().getGeo().getLng();
        HttpRequest getRequestUserWeather = HttpRequest.newBuilder()
                .uri(URI.create("https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&current=temperature_2m"))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> getResponseTodos = client.sendAsync(getRequestUserTodos, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<HttpResponse<String>> getResponsePosts = client.sendAsync(getRequestUserPosts, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<HttpResponse<String>> getResponseWeather = client.sendAsync(getRequestUserWeather, HttpResponse.BodyHandlers.ofString());
        CompletableFuture.allOf(getResponsePosts.exceptionally(ex -> {
            System.err.println("Post Error" + ex.getMessage());
            return null;
        }), getResponseTodos.exceptionally(ex -> {
            System.err.println("Todos Error" + ex.getMessage());
            return null;
        }), getResponseWeather.exceptionally(ex -> {
            System.err.println("Weather Error" + ex.getMessage());
            return null;
        })).join();

        List<Posts> postsList = objectMapper.readValue(getResponsePosts.join().body(), new TypeReference<>() {
        });
        List<Todos> todosList = objectMapper.readValue(getResponseTodos.join().body(), new TypeReference<>() {
        });
        Weather weather = objectMapper.readValue(getResponseWeather.join().body(), Weather.class);
        return new UserReport(user, postsList, todosList, weather);
    }
}
