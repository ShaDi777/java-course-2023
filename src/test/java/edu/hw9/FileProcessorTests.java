package edu.hw9;

import edu.hw9.task2.FileProcessor;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

public class FileProcessorTests {
    @TempDir
    private static Path path;

    @BeforeAll
    public static void initPath() throws IOException {
        resolveFiles(path.resolve("1"), ".txt", 15);
        resolveFiles(path.resolve("2"), ".zip", 7);
        resolveFiles(path.resolve("3"), ".tar", 3);
        resolveFiles(path.resolve("4"), ".trew", 1);
        resolveFiles(path.resolve("5"), ".fd", 2);
        resolveFiles(path.resolve("6"), ".exe", 11);
        resolveFiles(path.resolve("7"), ".docx", 5);
        Files.writeString(path.resolve("1").resolve("1.txt"), "0123456789");
    }

    private static void resolveFiles(Path dir, String extension, int number) throws IOException {
        Files.createDirectories(dir);
        for (int i = 0; i < number; i++) {
            Path filePath = dir.resolve(i + extension);
            Files.createFile(filePath);
        }
    }

    @Test
    public void findDirectoriesWithMoreThanNumberFiles() {
        List<String> actual = FileProcessor.findDirectoriesWithFileCount(path, 3);
        List<String> directoryNames = actual.stream()
            .map(el -> el.substring(el.lastIndexOf(FileSystems.getDefault().getSeparator()) + 1)).toList();
        assertThat(directoryNames).containsExactlyInAnyOrder("1", "2", "3", "6", "7");
    }

    @Test
    public void filterFilesByExtensionPredicate() {
        List<String> actual = FileProcessor.findFilesWithExtension(path, ".tar");
        List<String> fileNames = actual.stream()
            .map(el -> el.substring(el.lastIndexOf(FileSystems.getDefault().getSeparator()) + 1)).toList();
        assertThat(fileNames).containsExactlyInAnyOrder("0.tar", "1.tar", "2.tar");
    }

    @Test
    public void filterFilesBySizePredicate() {
        List<String> actual = FileProcessor.findFilesWithSize(path, 10);
        List<String> fileNames = actual.stream()
            .map(el -> el.substring(el.lastIndexOf(FileSystems.getDefault().getSeparator()) + 1)).toList();
        assertThat(fileNames).containsExactlyInAnyOrder("1.txt");
    }
}
