package edu.project3;

import edu.project3.models.Configuration;
import edu.project3.services.ArgumentParser;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Configuration configuration = ArgumentParser.parse(args);
    }
}
