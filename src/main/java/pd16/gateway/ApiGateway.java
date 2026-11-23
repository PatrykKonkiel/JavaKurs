package pd16.gateway;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import pd16.*;
import pd16.entity.Posts;
import pd16.entity.Todos;
import pd16.entity.User;
import pd16.entity.Weather;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor(staticName = "of")
public class ApiGateway {
    private final UserGateway userGateway;
    private final PostsGateway postsGateway;
    private final TodosGateway todosGateway;
    private final WeatherGateway weatherGateway;

    public UserReport getUserReport(Long userId) throws IOException, InterruptedException {
        User user = Try.of(() -> userGateway.getUser(userId))
                .onFailure(throwable -> System.err.println("API failed " + throwable.getMessage())).getOrNull();
        List<Posts> postsList = Try.of(() -> postsGateway.getPosts(userId))
                .onFailure(throwable -> System.err.println("API failed " + throwable.getMessage()))
                .getOrElse(List.of());
        List<Todos> todosList = Try.of(() -> todosGateway.getTodos(userId))
                .onFailure(throwable -> System.err.println("API failed " + throwable.getMessage())).getOrElse(List.of());
        String longitude = user.getAddress().getGeo().getLongitude();
        String latitude = user.getAddress().getGeo().getLatitude();
        Weather weather = Try.of(() -> weatherGateway.getWeather(latitude, longitude))
                .onFailure(throwable -> System.err.println("API failed " + throwable.getMessage())).getOrNull();
        return new UserReport(user, postsList, todosList, weather);
    }
}
