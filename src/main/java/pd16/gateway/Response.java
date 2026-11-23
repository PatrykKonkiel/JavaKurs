package pd16.gateway;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor(staticName = "of")
public class Response {
    private final HttpClient client;
    public HttpResponse<String> getResponse(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
        if(send.statusCode() != 200){
            throw new IOException("User API returned status " + send.statusCode());
        }
        return send;
    }
}
