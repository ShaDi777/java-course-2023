package edu.project3.services.ReportSavers;

import edu.project3.models.TableResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MarkdownReportSaver extends ReportSaver {
    public static final String EXTENSION = ".md";

    private final File result;

    public MarkdownReportSaver(String path) {
        result = !path.endsWith(EXTENSION)
            ? new File(path + EXTENSION)
            : new File(path);

        try {
            result.createNewFile();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
            writeLine("## Report from " + LocalDateTime.now().format(formatter));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addTable(TableResult table) {
        try {
            writeLine("### " + table.getTableTitle());

            int[] columnSizes = getColumnSizes(table.getTitles(), table.getRows());
            writeLine(buildRowWithSeparator(columnSizes, table.getTitles(), '|'));
            writeLine(buildSeparator(columnSizes));
            for (List<String> row : table.getRows()) {
                writeLine(buildRowWithSeparator(columnSizes, row, '|'));
            }
            writeLine("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeLine(String line) throws IOException {
        Files.write(result.toPath(), (line + "\n").getBytes(), StandardOpenOption.APPEND);
    }

    private String buildSeparator(int[] columnSizes) {
        StringBuilder sb = new StringBuilder();
        for (int columnSize : columnSizes) {
            sb.append("|:");
            sb.append("-".repeat(columnSize - 2));
            sb.append(":");
        }
        sb.append('|');
        return sb.toString();
    }
}
