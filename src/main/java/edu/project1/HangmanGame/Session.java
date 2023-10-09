package edu.project1.HangmanGame;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class Session {
    private static final char FILLER = '*';

    private final @NotNull String answer;
    private final int maxAttempts;
    private final char[] userAnswer;
    private int attempts;
    private final Set<Character> usedLetters;

    public Session(@NotNull String answer, int maxAttempts) {
        if (!answer.matches("\\pL+")) {
            throw new IllegalArgumentException("Word must contain only alphabetic characters!");
        }

        this.answer = answer;
        this.maxAttempts = maxAttempts;

        this.attempts = 0;
        usedLetters = new HashSet<>();
        this.userAnswer = new char[answer.length()];
        Arrays.fill(this.userAnswer, FILLER);
    }

    public @NotNull GuessResult guess(char guess) {
        if (attempts >= maxAttempts) {
            return new GuessResult.Defeat(userAnswer, attempts, maxAttempts);
        }
        if (usedLetters.contains(guess)) {
            return new GuessResult.RepeatedGuess(userAnswer);
        }
        usedLetters.add(guess);

        boolean isCorrectGuess = false;
        boolean hasHiddenLetters = false;
        for (int i = 0; i < answer.length(); i++) {
            if (guess == answer.charAt(i)) {
                userAnswer[i] = guess;
                isCorrectGuess = true;
            }
            if (userAnswer[i] == FILLER) {
                hasHiddenLetters = true;
            }
        }

        if (isCorrectGuess) {
            return hasHiddenLetters
                ? new GuessResult.SuccessfulGuess(userAnswer, attempts, maxAttempts)
                : new GuessResult.Win(userAnswer, attempts, maxAttempts);
        } else {
            attempts++;
            return attempts >= maxAttempts
                ? new GuessResult.Defeat(userAnswer, attempts, maxAttempts)
                : new GuessResult.FailedGuess(userAnswer, attempts, maxAttempts);
        }
    }

    public @NotNull GuessResult giveUp() {
        return new GuessResult.Defeat(userAnswer, attempts, maxAttempts);
    }

    public @NotNull GuessResult retryGuess() {
        return new GuessResult.RetryIncorrectGuess(userAnswer);
    }
}
