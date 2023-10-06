package edu.project1.Dictionaries;

import java.util.Locale;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class DemoWordDictionary implements WordDictionary {
    private final Random random = new Random();
    private final String[] words = {
        "hello", "world", "cat", "bicycle", "java", "program"
    };

    @Override
    public @NotNull String getRandomWord() {
        return words[random.nextInt(words.length)].toLowerCase(Locale.ROOT);
    }
}
