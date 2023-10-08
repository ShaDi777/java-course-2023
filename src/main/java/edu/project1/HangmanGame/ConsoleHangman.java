package edu.project1.HangmanGame;

import edu.project1.Dictionaries.WordDictionary;
import java.util.Locale;
import java.util.Scanner;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleHangman {
    private static final String GIVE_UP_LINE = "end";

    private final WordDictionary wordDictionary;
    private final int maxAttempts;
    private Session session;

    public ConsoleHangman(WordDictionary wordDictionary, int maxAttempts) {
        this.wordDictionary = wordDictionary;
        this.maxAttempts = maxAttempts;
        this.session = new Session(wordDictionary.getRandomWord().toLowerCase(), maxAttempts);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        session = new Session(wordDictionary.getRandomWord().toLowerCase(), maxAttempts);

        System.out.println("---- CONSOLE HANGMAN GAME ----");
        System.out.printf("You have %d attempts.%n", maxAttempts);
        System.out.printf("If you want to give up and finish the game type: %s%n", GIVE_UP_LINE);
        while (true) {
            System.out.println("===================");
            System.out.print("Guess a letter:");

            String input = scanner.hasNext() ? scanner.next() : null;
            System.out.println();

            GuessResult guessResult = tryGuess(input);
            printState(guessResult);

            if (guessResult instanceof GuessResult.Win
                || guessResult instanceof GuessResult.Defeat) {
                break;
            }
        }
    }

    protected GuessResult tryGuess(String input) {
        if (input == null || input.equalsIgnoreCase(GIVE_UP_LINE)) {
            return session.giveUp();
        }
        if (!input.matches("\\pL")) {
            return session.retryGuess();
        }

        return session.guess(input.toLowerCase(Locale.ROOT).charAt(0));
    }

    private void printState(GuessResult guess) {
        System.out.println(guess.message());
        System.out.println("The word: " + String.valueOf(guess.state()));
    }
}
