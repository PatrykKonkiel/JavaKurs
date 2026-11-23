package pd16.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import pd16.entity.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;

@RequiredArgsConstructor(staticName = "of")
public class UserGateway {
    private final ObjectMapper objectMapper;
    private final Response response;

    public User getUser(Long userId) throws IOException, InterruptedException {
        HttpRequest getRequestUser = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();
        return objectMapper.readValue(response.getResponse(getRequestUser).body(), User.class);
    }
}

