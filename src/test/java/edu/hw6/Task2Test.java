package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    private final static String TEST_FILES = "src/test/java/edu/hw6/testFiles/Task2/";

    @Test
    void copyNonExistentFileTest() {
        Path p = Path.of(TEST_FILES + "no_such_file.txt");
        assertThrows(RuntimeException.class, () -> Cloner.cloneFile(p));
    }

    @Test
    void copyDirectoryTest() {
        Path path = Path.of(TEST_FILES + "sampleDirectory");
        Path pathExpected0 = Path.of(TEST_FILES + "sampleDirectory" + Cloner.COPY_APPENDER_NAME);
        Path pathExpected1 = Path.of(TEST_FILES + "sampleDirectory" + Cloner.COPY_APPENDER_NAME + " (1)");

        Cloner.cloneFile(path);
        Cloner.cloneFile(path);

        assertThat(Files.exists(pathExpected0)).isTrue();
        assertThat(Files.exists(pathExpected1)).isTrue();
    }

    @Test
    void copyFileWithoutExtensionTest() throws IOException {
        Path path = Path.of(TEST_FILES + "sample");
        Path pathExpected = Path.of(TEST_FILES + "sample" + Cloner.COPY_APPENDER_NAME);

        Cloner.cloneFile(path);

        assertThat(Files.exists(pathExpected)).isTrue();
        assertThat(Files.readAllLines(pathExpected)).isEqualTo(Files.readAllLines(path));
    }

    @Test
    void copyFileWithExtensionTest() throws IOException {
        Path path = Path.of(TEST_FILES + "sampleExt.txt");
        Path pathExpected = Path.of(TEST_FILES + "sampleExt" + Cloner.COPY_APPENDER_NAME + ".txt");

        Cloner.cloneFile(path);

        assertThat(Files.exists(pathExpected)).isTrue();
        assertThat(Files.readAllLines(pathExpected)).isEqualTo(Files.readAllLines(path));
    }

    @BeforeEach
    @AfterEach
    public void clearTestDirectory() {
        try (Stream<Path> stream = Files.list(Path.of(TEST_FILES))) {
            stream.filter(p -> p.getFileName().toString().contains(Cloner.COPY_APPENDER_NAME))
                .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
