package ru.kpfu.novikova.services;

import org.springframework.stereotype.Component;
import ru.kpfu.novikova.model.weather.ConverterWelcome;
import ru.kpfu.novikova.model.weather.WeatherInfo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class NetworkService {
    public static String apiKey = "6faded995f07a67bd6431a5176bb4640";
    public WeatherInfo fetchData(String city) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return ConverterWelcome.fromJsonString(response.body());
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return new WeatherInfo();
    }

}
