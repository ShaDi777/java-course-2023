package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.hw6.AbstractFilter.globMatches;
import static edu.hw6.AbstractFilter.largerThan;
import static edu.hw6.AbstractFilter.magicNumber;
import static edu.hw6.AbstractFilter.regexContains;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    private final static String TEST_DIRECTORY = "src/test/java/edu/hw6/testFiles/Task3/";

    @Test
    void testAbstractFilter() {
        AbstractFilter regularFile = Files::isRegularFile;
        AbstractFilter readable = Files::isReadable;

        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(largerThan(10_000))
            .and(magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[i]"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of(TEST_DIRECTORY), filter)) {
            assertThat(entries).containsExactly(Path.of(TEST_DIRECTORY + "linux.png"));
        } catch (IOException ignored) {
        }
    }

    @Test
    void testAbstractFilterRegular() {
        AbstractFilter filter = Files::isRegularFile;

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of(TEST_DIRECTORY), filter)) {
            assertThat(entries).hasSameElementsAs(
                List.of(
                    Path.of(TEST_DIRECTORY + "file"),
                    Path.of(TEST_DIRECTORY + "fileWithExtension.txt"),
                    Path.of(TEST_DIRECTORY + "linux.png")
                )
            );
        } catch (IOException ignored) {
        }
    }
}
