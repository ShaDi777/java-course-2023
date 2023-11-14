package edu.project3;

import edu.project3.models.Configuration;
import edu.project3.models.OutputFormat;
import edu.project3.services.ArgumentParser;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ArgumentParserTests {
    @Test
    void everyFlagIsPresented() {
        String path = "logs/2023*";
        String fromDate = "2023-08-31";
        String toDate = "2023-10-31";
        String format = "adoc";
        String[] args = {"--path", path, "--from", fromDate, "--to", toDate, "--format", format};

        Configuration config = ArgumentParser.parse(args);

        assertThat(config.path()).isEqualTo(path);
        assertThat(config.from().toString()).isEqualTo(fromDate);
        assertThat(config.to().toString()).isEqualTo(toDate);
        assertThat(config.format()).isEqualTo(OutputFormat.ADOC);
    }

    @Test
    void remotePath() {
        String path = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        String[] args = {"--path", path};

        Configuration config = ArgumentParser.parse(args);

        assertThat(config.path()).isEqualTo(path);
    }

    @Test
    void formatAndPath() {
        String path = "logs/**/2023-08-31.txt";
        String format = "MarkDown";
        String[] args = {"--format", format, "--path", path};

        Configuration config = ArgumentParser.parse(args);

        assertThat(config.path()).isEqualTo(path);
        assertThat(config.format()).isEqualTo(OutputFormat.MARKDOWN);
    }
}
