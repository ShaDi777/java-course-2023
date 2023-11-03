package edu.project3.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class LogParser {
    private LogParser() {
    }

    public static void parseFrom(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(System.out::println);
        } catch (IOException ignored) { }
    }

    // TODO fix http check
    private static File openFromPath(String path) {
        if (path.startsWith("http")) {
            // TODO send request?
            return null;
        }
        return new File(path);
    }
}
