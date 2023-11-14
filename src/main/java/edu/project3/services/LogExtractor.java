package edu.project3.services;

import edu.project3.entities.Log;
import edu.project3.models.Configuration;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class LogExtractor {
    private static final int TIMEOUT_SEC = 15;

    private LogExtractor() {
    }

    public static List<Log> extractFrom(Configuration configuration) {
        List<String> logStrings = new ArrayList<>();
        if (configuration.isRemotePath()) {
            logStrings = fetchFromRemote(configuration.path());
        } else {
            for (String path : configuration.listFiles()) {
                try {
                    logStrings.addAll(Files.readAllLines(Path.of(path)));
                } catch (IOException ignored) { }
            }
        }

        return parseFromStrings(logStrings, configuration);
    }

    private static List<String> fetchFromRemote(String path) {
        try (HttpClient client = HttpClient.newBuilder().build()) {
            var request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .timeout(Duration.ofSeconds(TIMEOUT_SEC))
                .GET()
                .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Log> logs = new ArrayList<>();
            return Arrays.stream(response.body().split("\n")).toList();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Log> parseFromStrings(List<String> strings, Configuration configuration) {
        List<Log> logs = new ArrayList<>();
        for (String line : strings) {
            Log parsedLog = Log.parse(line);
            if (parsedLog.timeLocal().toLocalDate().isAfter(configuration.from())
                && parsedLog.timeLocal().toLocalDate().isBefore(configuration.to())) {
                logs.add(parsedLog);
            }
        }
        return logs;
    }
}
