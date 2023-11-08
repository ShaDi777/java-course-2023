package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private final static String BASE_URL = "https://hacker-news.firebaseio.com/v0/";
    private static final int TIMEOUT_SECONDS = 15;

    private final HttpClient client;

    public HackerNews(HttpClient client) {
        this.client = client;
    }

    public long[] hackerNewsTopStories() {
        var request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "topstories.json"))
            .timeout(Duration.ofSeconds(TIMEOUT_SECONDS))
            .GET()
            .build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String[] data = response.body().split(",");
            if (data.length > 0) {
                data[0] = data[0].substring(1);

                int lastIndex = data.length - 1;
                data[lastIndex] = data[lastIndex].substring(0, data[lastIndex].length() - 1);
            }

            return Arrays.stream(data).mapToLong(Long::parseLong).toArray();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String news(long id) {
        var request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + String.format("item/%d.json", id)))
            .timeout(Duration.ofSeconds(TIMEOUT_SECONDS))
            .GET()
            .build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Pattern pattern = Pattern.compile("\"title\":\"(.+?)\"");
            Matcher matcher = pattern.matcher(response.body());

            return matcher.find() ? matcher.group(1) : "";
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
