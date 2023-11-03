package edu.project3.models;

import java.time.LocalDate;
import java.util.List;

public record Configuration(
    List<String> paths,
    LocalDate from,
    LocalDate to,
    OutputFormat format
) {
    public Configuration() {
        this(List.of(), LocalDate.MIN, LocalDate.MAX, OutputFormat.TXT);
    }

    public Configuration(List<String> paths) {
        this(paths, LocalDate.MIN, LocalDate.MAX, OutputFormat.TXT);
    }

    public Configuration(List<String> paths, LocalDate from, LocalDate to) {
        this(paths, from, to, OutputFormat.TXT);
    }

    public Configuration(List<String> paths, OutputFormat format) {
        this(paths, LocalDate.MIN, LocalDate.MAX, format);
    }

    public Configuration addPath(String path) {
        paths.add(path);
        return this;
    }

    public Configuration withFromDate(LocalDate from) {
        return new Configuration(paths, from, to, format);
    }

    public Configuration withToDate(LocalDate to) {
        return new Configuration(paths, from, to, format);
    }

    public Configuration withFormat(OutputFormat format) {
        return new Configuration(paths, from, to, format);
    }
}
