package com.my.javafeatures.java_9;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class HttpClientTest {

    @Test
    @SneakyThrows
    void get() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://catfact.ninja/fact"))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode(), is(200));
        assertThat(response.body(), notNullValue());
    }

    @Test
    @SneakyThrows
    void post() {
        String dataToSend = "some data";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/post"))
                .POST(HttpRequest.BodyPublishers.ofString(dataToSend))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode(), is(200));
        assertThat(response.body(), containsString(dataToSend));
    }

}
