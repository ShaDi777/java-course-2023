package edu.project3.services;

import edu.project3.models.Configuration;
import edu.project3.models.OutputFormat;
import java.time.LocalDate;

public final class ArgumentParser {
    private ArgumentParser() {
    }

    public static Configuration parse(String[] args) {
        Configuration config = new Configuration();

        String previousAction = "";
        for (String arg : args) {
            if (arg.startsWith("--")) {
                previousAction = arg;
                continue;
            }
            switch (previousAction) {
                case "--path":
                    config = config.withPath(arg);
                    break;
                case "--from":
                    config = config.withFromDate(LocalDate.parse(arg));
                    break;
                case "--to":
                    config = config.withToDate(LocalDate.parse(arg));
                    break;
                case "--format":
                    config = config.withFormat(OutputFormat.valueOf(arg.toUpperCase()));
                    break;
                default:
                    continue;
            }
        }

        return config;
    }
}
