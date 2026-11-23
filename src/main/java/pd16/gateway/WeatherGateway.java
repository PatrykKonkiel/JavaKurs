package pd16.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import pd16.entity.Weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;

@RequiredArgsConstructor(staticName = "of")
public class WeatherGateway {
    private final ObjectMapper objectMapper;
    private final Response response;

    public Weather getWeather(String latitude, String longitude) throws IOException, InterruptedException {
        HttpRequest getRequestUserWeather = HttpRequest.newBuilder()
                .uri(URI.create("https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&current=temperature_2m"))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();
        return objectMapper.readValue(response.getResponse(getRequestUserWeather).body(), Weather.class);
    }
}
