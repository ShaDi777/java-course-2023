package edu.project3.services.Analyzers;

import edu.project3.entities.Log;
import edu.project3.models.Configuration;
import edu.project3.models.TableResult;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
                    file.lastIndexOf('\\'),
                    file.lastIndexOf('/')
                ) + 1
            );
            tableResult.addRow(List.of(
                "Files",
                '`' + fileName + '`'
            ));
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        tableResult.addRow(List.of(
            "Start date",
            configuration.from() == LocalDate.MIN ? "-" : configuration.from().format(format)
        ));
        tableResult.addRow(List.of(
            "End date",
            configuration.to() == LocalDate.MAX ? "-" : configuration.to().format(format)
        ));
        tableResult.addRow(List.of(
            "Requests amount",
            String.valueOf(logs.size())
        ));
        tableResult.addRow(List.of(
            "Average body size",
            String.valueOf(
                (long) logs.stream()
                    .mapToLong(Log::bodyBytesSent)
                    .average()
                    .orElse(0)
            ) + 'b'
        ));
        return tableResult;
    }
}
