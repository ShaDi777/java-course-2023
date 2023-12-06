package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public final class FileProcessor {
    private static final ForkJoinPool FORK_JOIN_POOL = ForkJoinPool.commonPool();

    private FileProcessor() {
    }

    public static List<String> findDirectoriesWithFileCount(Path root, int minFileCount) {
        return FORK_JOIN_POOL.invoke(
            new FileTask(
                root,
                (Path p) -> {
                    if (!Files.isDirectory(p)) {
                        return false;
                    }

                    try (Stream<Path> paths = Files.list(p)) {
                        return paths.count() >= minFileCount;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            )
        );
    }

    public static List<String> findFilesWithExtension(Path root, String extension) {
        return FORK_JOIN_POOL.invoke(
            new FileTask(
                root,
                (Path p) -> p.toString().endsWith(extension)
            )
        );
    }

    public static List<String> findFilesWithSize(Path root, int minSizeBytes) {
        return FORK_JOIN_POOL.invoke(
            new FileTask(
                root,
                (Path p) -> {
                    try {
                        return Files.isRegularFile(p) && Files.size(p) >= minSizeBytes;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            )
        );
    }
}
