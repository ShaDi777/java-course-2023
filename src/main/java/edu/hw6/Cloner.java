package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class Cloner {
    public final static String COPY_APPENDER_NAME = " — копия";

    private Cloner() {
    }

    public static void cloneFile(Path path) {
        String fileName = path.getFileName().toString();
        int indexExtension = fileName.lastIndexOf('.');
        if (indexExtension == -1) {
            indexExtension = fileName.length();
        }
        String fileNameNoExtension = fileName.substring(0, indexExtension);
        String fileExtension = fileName.substring(indexExtension);

        int copyIndex = 0;
        while (Files.exists(path.getParent().resolve(getCopyName(fileNameNoExtension, fileExtension, copyIndex)))) {
            copyIndex++;
        }

        try {
            Files.copy(
                path,
                path.getParent().resolve(getCopyName(fileNameNoExtension, fileExtension, copyIndex)),
                StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getCopyName(String fileName, String fileExtension, int copyIndex) {
        if (copyIndex == 0) {
            return fileName + COPY_APPENDER_NAME + fileExtension;
        }
        return fileName + COPY_APPENDER_NAME + " (" + copyIndex + ')' + fileExtension;
    }
}
