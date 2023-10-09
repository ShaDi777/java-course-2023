package edu.project1.HangmanGame;

import edu.project1.Dictionaries.WordDictionary;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class HangmanTest {

    private class TestDictionary implements WordDictionary {
        @Override
        public @NotNull String getRandomWord() {
            return "abcde";
        }
    }

    private static final String ANSWER = "abcde";
    private static final String WRONG_GUESSES = "fghijklmnopqrstuvwxyz1234567890!@#$%^&*()_-=+;:./?<>";
    private static final int MAX_ATTEMPTS = 5;
    private final ConsoleHangman testGame = new ConsoleHangman(new TestDictionary(), MAX_ATTEMPTS);

    @ParameterizedTest
    @ValueSource(
        strings = {
            "",
            "1",
            ":",
            "*",
            "_",
            "@",
            "AA",
            "aa",
            "1234567890"
        }
    )
    void TestIncorrectInput(String input) {
        // Arrange
        // Act
        GuessResult result = testGame.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(GuessResult.RetryIncorrectGuess.class);
    }

    @Test
    void TestGiveUpNullInput() {
        // Arrange
        String input = null;

        // Act
        GuessResult result = testGame.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(GuessResult.Defeat.class);
    }

    @Test
    void TestGiveUpEndInput() {
        // Arrange
        String input = "End";

        // Act
        GuessResult result = testGame.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(GuessResult.Defeat.class);
    }

    // Repeated guesses does not affect attempts
    // Test that game does not finish after MAX_ATTEMPTS repeats
    @Test
    void TestRepeatedGuess() {
        // Arrange
        String input = String.valueOf(ANSWER.charAt(0));

        // Act
        GuessResult result = testGame.tryGuess(input);
        for (int i = 0; i <= MAX_ATTEMPTS + 1; i++) {
            GuessResult resultRepeat = testGame.tryGuess(input);
            assertThat(resultRepeat).isInstanceOf(GuessResult.RepeatedGuess.class);
        }

        // Assert
        assertThat(result).isInstanceOf(GuessResult.SuccessfulGuess.class);
    }

    @Test
    void TestDefeat() {
        // Arrange
        List<String> inputs = new ArrayList<>();
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            char current = WRONG_GUESSES.charAt(i);
            inputs.add(String.valueOf(current));
        }

        // Act
        for (int i = 0; i < MAX_ATTEMPTS - 1; i++) {
            GuessResult result = testGame.tryGuess(inputs.get(i));
            assertThat(result).isInstanceOf(GuessResult.FailedGuess.class);
        }
        GuessResult result = testGame.tryGuess(inputs.get(inputs.size() - 1));

        // Assert
        assertThat(result).isInstanceOf(GuessResult.Defeat.class);
    }

    @Test
    void TestWin() {
        // Arrange
        List<String> inputs = new ArrayList<>();
        for (int i = 0; i < ANSWER.length(); i++) {
            char current = ANSWER.charAt(i);
            inputs.add(String.valueOf(current));
        }

        // Act
        for (int i = 0; i < ANSWER.length() - 1; i++) {
            GuessResult result = testGame.tryGuess(inputs.get(i));
            assertThat(result).isInstanceOf(GuessResult.SuccessfulGuess.class);
        }
        GuessResult result = testGame.tryGuess(inputs.get(inputs.size() - 1));

        // Assert
        assertThat(result).isInstanceOf(GuessResult.Win.class);
    }
}
