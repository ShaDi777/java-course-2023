package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FileTask extends RecursiveTask<List<String>> {
    private final Path root;
    private final Predicate<Path> isAcceptable;

    public FileTask(Path root, Predicate<Path> isAcceptable) {
        this.root = root;
        this.isAcceptable = isAcceptable;
    }

    @Override
    protected List<String> compute() {
        List<String> result = new ArrayList<>();
        List<FileTask> forkTasks = new ArrayList<>();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(root)) {
            for (Path path : paths) {
                if (Files.isDirectory(path)) {
                    var forkTask = new FileTask(path, isAcceptable);
                    forkTask.fork();
                    forkTasks.add(forkTask);
                }

                if (isAcceptable.test(path)) {
                    result.add(path.toString());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (var task : forkTasks) {
            result.addAll(task.join());
        }

        return result;
    }
}
