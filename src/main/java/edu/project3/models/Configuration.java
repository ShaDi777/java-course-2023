package edu.project3.models;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record Configuration(
    String path,
    LocalDate from,
    LocalDate to,
    OutputFormat format
) {
    public Configuration() {
        this("", LocalDate.MIN, LocalDate.MAX, OutputFormat.MARKDOWN);
    }

    public Configuration(String path) {
        this(path, LocalDate.MIN, LocalDate.MAX, OutputFormat.MARKDOWN);
    }

    public Configuration(String path, LocalDate from, LocalDate to) {
        this(path, from, to, OutputFormat.MARKDOWN);
    }

    public Configuration(String path, OutputFormat format) {
        this(path, LocalDate.MIN, LocalDate.MAX, format);
    }

    public Configuration withPath(String path) {
        return new Configuration(path, from, to, format);
    }

    public Configuration withFromDate(LocalDate from) {
        return new Configuration(path, from, to, format);
    }

    public Configuration withToDate(LocalDate to) {
        return new Configuration(path, from, to, format);
    }

    public Configuration withFormat(OutputFormat format) {
        return new Configuration(path, from, to, format);
    }

    public List<String> listFiles() {
        if (isRemotePath()) {
            return List.of(path);
        }

        try {
            Path root = Path.of(System.getProperty("user.dir"));
            int astrixIndex = path.indexOf('*');
            String startString = path()
                .substring(0, astrixIndex == -1
                                ? path.length()
                                : astrixIndex
            );

            Path startPath = Path.of(startString);
            if (!startString.isEmpty() && astrixIndex != -1 && !startString.endsWith("\\")) {
                startPath = startPath.getParent();
            }

            Finder finder = new Finder(path());
            if (!startPath.isAbsolute()) {
                finder = new Finder(root + "/" + path());
            }

            root = root.resolve(startPath);

            Files.walkFileTree(root, finder);
            return finder.files;
        } catch (IOException e) {
            return List.of();
        }
    }

    public boolean isRemotePath() {
        return path.startsWith("http");
    }

    private static class Finder extends SimpleFileVisitor<Path> {
        private final PathMatcher matcher;
        private final List<String> files = new ArrayList<>();

        Finder(String pattern) {
            matcher = FileSystems.getDefault()
                .getPathMatcher("glob:" + pattern.replaceAll("\\\\", "/"));
        }

        void find(Path file) {
            if (file != null && matcher.matches(file)) {
                files.add(file.toString());
            }
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            find(dir);
            if (Files.isReadable(dir)) {
                return super.preVisitDirectory(dir, attrs);
            }
            return FileVisitResult.SKIP_SIBLINGS;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            find(file);
            return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.SKIP_SUBTREE;
        }
    }
}
