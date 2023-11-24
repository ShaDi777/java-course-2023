package edu.hw6;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    private final static Path TEST_PATH = Path.of("src/test/java/edu/hw6/testFiles/Task4/test.txt");

    @Test
    void initChainedPrintWriterTest() throws IOException {
        List<String> input = List.of("Programming is learned by writing programs. ― Brian Kernighan");

        try (PrintWriter pw = OutputChain.createNewWriter(TEST_PATH)) {
            pw.println(input.get(0));
        } catch (IOException ignored) {
        }

        assertThat(Files.readAllLines(TEST_PATH)).isEqualTo(input);
    }
}
