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

public class AdocReportSaver extends ReportSaver {
    public static final String EXTENSION = ".adoc";
    private static final String TABLE_SYMBOLS = "|===";
    private static final char HEADER_SYMBOL = '=';

    private final File result;

    public AdocReportSaver(String path) {
        result = !path.endsWith(EXTENSION)
            ? new File(path + EXTENSION)
            : new File(path);

        try {
            result.createNewFile();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
            writeLine(String.valueOf(HEADER_SYMBOL).repeat(2)
                + "Report from " + LocalDateTime.now().format(formatter));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addTable(TableResult table) {
        try {
            writeLine(HEADER_SYMBOL + table.getTableTitle());

            writeLine(TABLE_SYMBOLS);
            int[] columnSizes = getColumnSizes(table.getTitles(), table.getRows());
            writeLine(buildRowWithSeparator(columnSizes, table.getTitles(), Constants.SEPARATOR_VERTICAL));
            for (List<String> row : table.getRows()) {
                writeLine(buildRowWithSeparator(columnSizes, row, Constants.SEPARATOR_VERTICAL));
            }
            writeLine(TABLE_SYMBOLS);
            writeLine(Constants.EMPTY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeLine(String line) throws IOException {
        Files.write(result.toPath(), (line + Constants.NEW_LINE).getBytes(), StandardOpenOption.APPEND);
    }
}
