package uz.jvh.telegramfrontend.util;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import uz.jvh.telegramfrontend.controller.exception.ApiException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestSender {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static <T, D> T post(String url, D data, Class<T> type) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(data)))
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 400) {
                throw objectMapper.readValue(response.body(), ApiException.class);
            }

            return  objectMapper.readValue(response.body(), type);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T get(String url, Class<T> type) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 400) {
                throw objectMapper.readValue(response.body(), ApiException.class);
            }

            return  objectMapper.readValue(response.body(), type);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
