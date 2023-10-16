package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Task1 {
    private Task1() {
    }

    public static String encodeAtbash(String text) {
        if (text == null) {
            return null;
        }

        List<Character> encodedText = new ArrayList<>();
        for (char c : text.toCharArray()) {
            if (!isLatinLetter(c)) {
                encodedText.add(c);
                continue;
            }
            encodedText.add(atbashChar(c));
        }

        return encodedText.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining());
    }

    private static boolean isLatinLetter(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    private static char atbashChar(char c) {
        if (Character.isUpperCase(c)) {
            return (char) ('A' + 'Z' - c);
        }
        return (char) ('a' + 'z' - c);
    }
}
