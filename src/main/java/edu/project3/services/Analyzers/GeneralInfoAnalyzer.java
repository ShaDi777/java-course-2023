package edu.project3.services.Analyzers;

import edu.project3.Constants;
import edu.project3.entities.Log;
import edu.project3.models.Configuration;
import edu.project3.models.TableResult;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class GeneralInfoAnalyzer implements Analyzer {
    private final Configuration configuration;

    public GeneralInfoAnalyzer(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public TableResult doAnalysis(List<Log> logs) {
        TableResult tableResult = new TableResult("General information", List.of("Metrics", "Value"));
        List<String> files = configuration.listFiles();
        for (String file : files) {
            String fileName = file.substring(
                Math.max(
                    file.lastIndexOf(Constants.WINDOWS_FILE_SEPARATOR),
                    file.lastIndexOf(Constants.UNIX_FILE_SEPARATOR)
                ) + 1
            );
            tableResult.addRow(List.of(
                "Files",
                Constants.FILE_ANNOTATION + fileName + Constants.FILE_ANNOTATION
            ));
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        tableResult.addRow(List.of(
            "Start date",
            configuration.from() == LocalDate.MIN
                ? String.valueOf(Constants.SEPARATOR_HORIZONTAL)
                : configuration.from().format(format)
        ));
        tableResult.addRow(List.of(
            "End date",
            configuration.to() == LocalDate.MAX
                ? String.valueOf(Constants.SEPARATOR_HORIZONTAL)
                : configuration.to().format(format)
        ));
        tableResult.addRow(List.of(
            "Requests amount",
            String.valueOf(logs.stream().filter(Objects::nonNull).count())
        ));
        tableResult.addRow(List.of(
            "Average body size",
            String.valueOf(
                (long) logs.stream()
                    .filter(Objects::nonNull)
                    .mapToLong(Log::bodyBytesSent)
                    .average()
                    .orElse(0)
            ) + Constants.BYTES_SYMBOL
        ));
        return tableResult;
    }
}
