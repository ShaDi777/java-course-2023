package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    default AbstractFilter and(AbstractFilter other) {
        return (path -> this.accept(path) && other.accept(path));
    }

    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter writable() {
        return Files::isWritable;
    }

    static AbstractFilter largerThan(long size) {
        return (path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                return false;
            }
        });
    }

    static AbstractFilter globMatches(String globPattern) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + globPattern);

        return (path -> matcher.matches(path.getFileName()));
    }

    static AbstractFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return (path -> pattern.matcher(path.toString()).find());
    }

    static AbstractFilter magicNumber(byte... bytes) {
        return (path -> {
            try {
                byte[] fileBytes = Files.readAllBytes(path);
                for (int i = 0; i < bytes.length && i < fileBytes.length; i++) {
                    if (fileBytes[i] != bytes[i]) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        });
    }
}