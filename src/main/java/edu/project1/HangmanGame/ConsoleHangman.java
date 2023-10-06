package edu.project1.HangmanGame;

import edu.project1.Dictionaries.WordDictionary;
import java.util.Locale;
import java.util.Scanner;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleHangman {
    private final WordDictionary wordDictionary;
    private final int maxAttempts;

    public ConsoleHangman(WordDictionary wordDictionary, int maxAttempts) {
        this.wordDictionary = wordDictionary;
        this.maxAttempts = maxAttempts;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Session session = new Session(wordDictionary.getRandomWord().toLowerCase(), maxAttempts);

        while (true) {
            System.out.println("===================");
            System.out.print("Guess a letter:");

            String input = scanner.hasNext() ? scanner.next() : null;
            System.out.println();

            GuessResult guessResult = tryGuess(session, input);
            printState(guessResult);

            if (guessResult instanceof GuessResult.Win
                || guessResult instanceof GuessResult.Defeat) {
                return;
            }
        }
    }

    private GuessResult tryGuess(Session session, String input) {
        if (input == null) {
            return session.giveUp();
        }
        if (input.length() != 1) {
            return session.retryGuess();
        }

        return session.guess(input.toLowerCase(Locale.ROOT).charAt(0));
    }

    private void printState(GuessResult guess) {
        System.out.println(guess.message());
        System.out.println("The word: " + String.valueOf(guess.state()));
    }
}
