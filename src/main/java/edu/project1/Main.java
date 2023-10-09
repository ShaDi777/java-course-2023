package edu.project1;

import edu.project1.Dictionaries.DemoWordDictionary;
import edu.project1.HangmanGame.ConsoleHangman;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static int MAX_ATTEMPTS = 5;

    private Main() {
    }

    public static void main(String[] args) {
        ConsoleHangman game = new ConsoleHangman(new DemoWordDictionary(), MAX_ATTEMPTS);
        game.run();
    }
}
