package edu.project1.HangmanGame;

import org.jetbrains.annotations.NotNull;

public sealed interface GuessResult {
    char[] state();

    int attempt();

    int maxAttempts();

    @NotNull String message();

    record Defeat(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You lost!\n";
        }
    }

    record Win(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You won!\n";
        }
    }

    record SuccessfulGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "Hit!\n";
        }
    }

    record FailedGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return String.format("Missed, mistake %d out of %d.\n", attempt, maxAttempts);
        }
    }

    record RepeatedGuess(char[] state) implements GuessResult {
        @Override
        public int attempt() {
            return 0;
        }

        @Override
        public int maxAttempts() {
            return 0;
        }

        @Override
        public @NotNull String message() {
            return "You have already chosen this letter, try another one!\n";
        }
    }

    record RetryIncorrectGuess(char[] state) implements GuessResult {
        @Override
        public int attempt() {
            return 0;
        }

        @Override
        public int maxAttempts() {
            return 0;
        }

        @Override
        public @NotNull String message() {
            return "Try again! You can guess only one letter at a time.\n";
        }
    }
}
