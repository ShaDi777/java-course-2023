package edu.project1;

import edu.project1.Dictionaries.WordDictionary;
import edu.project1.HangmanGame.ConsoleHangman;
import edu.project1.HangmanGame.GuessResult;
import edu.project1.HangmanGame.Session;
import java.lang.reflect.Method;
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
    private final Session testSession = new Session((new TestDictionary()).getRandomWord(), MAX_ATTEMPTS);
    private final ConsoleHangman testGame = new ConsoleHangman(new TestDictionary(), MAX_ATTEMPTS);

    private Method getTryGuess() throws NoSuchMethodException {
        Method testMethod = ConsoleHangman.class.getDeclaredMethod("tryGuess", Session.class, String.class);
        testMethod.setAccessible(true);
        return testMethod;
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "",
            "AA",
            "aa",
            "1234567890"
        }
    )
    void TestIncorrectInput(String input) throws Exception {
        // Arrange
        Method tryGuess = getTryGuess();

        // Act
        GuessResult result = (GuessResult) tryGuess.invoke(testGame, testSession, input);

        // Assert
        assertThat(result).isInstanceOf(GuessResult.RetryIncorrectGuess.class);
    }

    @Test
    void TestGiveUp() throws Exception {
        // Arrange
        String input = null;
        Method tryGuess = getTryGuess();

        // Act
        GuessResult result = (GuessResult) tryGuess.invoke(testGame, testSession, input);

        // Assert
        assertThat(result).isInstanceOf(GuessResult.Defeat.class);
    }

    // Repeated guesses does not affect attempts
    // Test that game does not finish after MAX_ATTEMPTS repeats
    @Test
    void TestRepeatedGuess() throws Exception {
        // Arrange
        String input = String.valueOf(ANSWER.charAt(0));
        Method tryGuess = getTryGuess();

        // Act
        GuessResult result = (GuessResult) tryGuess.invoke(testGame, testSession, input);
        for (int i = 0; i <= MAX_ATTEMPTS + 1; i++) {
            GuessResult resultRepeat = (GuessResult) tryGuess.invoke(testGame, testSession, input);
            assertThat(resultRepeat).isInstanceOf(GuessResult.RepeatedGuess.class);
        }

        // Assert
        assertThat(result).isInstanceOf(GuessResult.SuccessfulGuess.class);
    }

    @Test
    void TestDefeat() throws Exception {
        // Arrange
        List<String> inputs = new ArrayList<>();
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            char current = WRONG_GUESSES.charAt(i);
            inputs.add(String.valueOf(current));
        }
        Method tryGuess = getTryGuess();

        // Act
        for (int i = 0; i < MAX_ATTEMPTS - 1; i++) {
            GuessResult result = (GuessResult) tryGuess.invoke(testGame, testSession, inputs.get(i));
            assertThat(result).isInstanceOf(GuessResult.FailedGuess.class);
        }
        GuessResult result = (GuessResult) tryGuess.invoke(testGame, testSession, inputs.get(inputs.size() - 1));

        // Assert
        assertThat(result).isInstanceOf(GuessResult.Defeat.class);
    }

    @Test
    void TestWin() throws Exception {
        // Arrange
        List<String> inputs = new ArrayList<>();
        for (int i = 0; i < ANSWER.length(); i++) {
            char current = ANSWER.charAt(i);
            inputs.add(String.valueOf(current));
        }
        Method tryGuess = getTryGuess();

        // Act
        for (int i = 0; i < ANSWER.length() - 1; i++) {
            GuessResult result = (GuessResult) tryGuess.invoke(testGame, testSession, inputs.get(i));
            assertThat(result).isInstanceOf(GuessResult.SuccessfulGuess.class);
        }
        GuessResult result = (GuessResult) tryGuess.invoke(testGame, testSession, inputs.get(inputs.size() - 1));

        // Assert
        assertThat(result).isInstanceOf(GuessResult.Win.class);
    }
}
