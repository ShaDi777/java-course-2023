package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public final class OutputChain {
    private OutputChain() {
    }

    public static PrintWriter createNewWriter(Path path) throws IOException {
        var outputStream = Files.newOutputStream(path);
        var checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
        var bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
        var outputStreamWriter = new OutputStreamWriter(bufferedOutputStream);

        return new PrintWriter(outputStreamWriter);
    }
}
