package edu.project3;

import edu.project3.entities.Log;
import edu.project3.models.Configuration;
import edu.project3.services.Analyzers.Analyzer;
import edu.project3.services.Analyzers.GeneralInfoAnalyzer;
import edu.project3.services.Analyzers.HourDistributionAnalyzer;
import edu.project3.services.Analyzers.MethodAnalyzer;
import edu.project3.services.Analyzers.RequestResourceAnalyzer;
import edu.project3.services.Analyzers.StatusCodeAnalyzer;
import edu.project3.services.ArgumentParser;
import edu.project3.services.LogExtractor;
import edu.project3.services.ReportSavers.AdocReportSaver;
import edu.project3.services.ReportSavers.MarkdownReportSaver;
import edu.project3.services.ReportSavers.ReportSaver;
import java.util.List;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Configuration configuration = ArgumentParser.parse(args);
        if (configuration.path().isBlank()) {
            System.err.println("Path can not be empty!");
        }

        List<Log> logs = LogExtractor.extractFrom(configuration);

        List<Analyzer> analyzers = List.of(
            new GeneralInfoAnalyzer(configuration),
            new RequestResourceAnalyzer(),
            new StatusCodeAnalyzer(),
            new MethodAnalyzer(),
            new HourDistributionAnalyzer()
        );

        String reportName = "src\\main\\java\\edu\\project3\\files\\report";
        ReportSaver saver = switch (configuration.format()) {
            case ADOC -> new AdocReportSaver(reportName);
            case MARKDOWN -> new MarkdownReportSaver(reportName);
            default -> throw new IllegalArgumentException("Unknown saving format");
        };

        for (var analyzer : analyzers) {
            saver.addTable(analyzer.doAnalysis(logs));
        }
    }
}
