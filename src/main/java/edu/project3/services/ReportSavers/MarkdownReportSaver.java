package edu.project3.services.ReportSavers;

import edu.project3.Constants;
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
    private static final char HEADER_SYMBOL = '#';
    private static final char CENTERING_SYMBOL = ':';

    private final File result;

    public MarkdownReportSaver(String path) {
        result = !path.endsWith(EXTENSION)
            ? new File(path + EXTENSION)
            : new File(path);

        try {
            result.createNewFile();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
            writeLine(String.valueOf(HEADER_SYMBOL).repeat(2)
                + " Report from " + LocalDateTime.now().format(formatter));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public void addTable(TableResult table) {
        try {
            writeLine(String.valueOf(HEADER_SYMBOL).repeat(3) + table.getTableTitle());

            int[] columnSizes = getColumnSizes(table.getTitles(), table.getRows());
            writeLine(buildRowWithSeparator(columnSizes, table.getTitles(), Constants.SEPARATOR_VERTICAL));
            writeLine(buildSeparator(columnSizes));
            for (List<String> row : table.getRows()) {
                writeLine(buildRowWithSeparator(columnSizes, row, Constants.SEPARATOR_VERTICAL));
            }
            writeLine(Constants.EMPTY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeLine(String line) throws IOException {
        Files.write(result.toPath(), (line + Constants.NEW_LINE).getBytes(), StandardOpenOption.APPEND);
    }

    private String buildSeparator(int[] columnSizes) {
        StringBuilder sb = new StringBuilder();
        for (int columnSize : columnSizes) {
            sb.append(Constants.SEPARATOR_VERTICAL);
            sb.append(CENTERING_SYMBOL);
            sb.append(String.valueOf(Constants.SEPARATOR_HORIZONTAL).repeat(columnSize - 2));
            sb.append(CENTERING_SYMBOL);
        }
        sb.append(Constants.SEPARATOR_VERTICAL);
        return sb.toString();
    }
}
