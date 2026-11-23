package pd16.gateway;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import pd16.entity.Posts;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

@RequiredArgsConstructor(staticName = "of")
public class PostsGateway {
    private final ObjectMapper objectMapper;
    private final Response response;

    public List<Posts> getPosts(Long userId) throws IOException, InterruptedException {
        HttpRequest getRequestUserPosts = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId + "/posts"))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();
        return objectMapper.readValue(response.getResponse(getRequestUserPosts).body(), new TypeReference<>() {
        });
    }
}
