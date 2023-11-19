package edu.project3;

import edu.project3.entities.Log;
import edu.project3.models.Configuration;
import edu.project3.models.TableResult;
import edu.project3.services.Analyzers.GeneralInfoAnalyzer;
import edu.project3.services.Analyzers.HourDistributionAnalyzer;
import edu.project3.services.Analyzers.MethodAnalyzer;
import edu.project3.services.Analyzers.RequestResourceAnalyzer;
import edu.project3.services.Analyzers.StatusCodeAnalyzer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AnalyzerTests {
    private final List<Log> logs = new ArrayList<Log>() {
        {
            add(null);
            add(Log.parse("- - - [17/May/2015:08:05:32 +0000] \"GET /file1 HTTP/1.1\" 200 2 \"-\" \"-\""));
            add(Log.parse("- - - [17/May/2015:09:05:32 +0000] \"POST /file2 HTTP/1.1\" 500 3 \"-\" \"-\""));
            add(Log.parse(
                "217.168.17.5 - 93.180.71.3 [17/May/2015:10:05:32 +0000] \"HEAD /file1 HTTP/1.1\" 404 4 \"-\" \"-\""));
        }
    };

    @Test
    void generalInfoAnalyzer() {
        Configuration configuration = new Configuration("path");
        TableResult table = new GeneralInfoAnalyzer(configuration).doAnalysis(logs);
        assertThat(table.getRows())
            .contains(List.of("Start date", "-"))
            .contains(List.of("End date", "-"))
            .contains(List.of("Requests amount", "3"))
            .contains(List.of("Average body size", "3b"));
    }

    @Test
    void statusCodeAnalyzer() {
        TableResult table = new StatusCodeAnalyzer().doAnalysis(logs);
        assertThat(table.getRows())
            .contains(List.of("200", "Ok", "1"))
            .contains(List.of("404", "Not Found", "1"))
            .contains(List.of("500", "Internal Server Error", "1"));
    }

    @Test
    void httpMethodAnalyzer() {
        TableResult table = new MethodAnalyzer().doAnalysis(logs);
        assertThat(table.getRows())
            .contains(List.of("GET", "1"))
            .contains(List.of("HEAD", "1"))
            .contains(List.of("POST", "1"));
    }

    @Test
    void requestedResourceAnalyzer() {
        TableResult table = new RequestResourceAnalyzer().doAnalysis(logs);
        assertThat(table.getRows())
            .contains(List.of("/file1", "2"))
            .contains(List.of("/file2", "1"));
    }

    @Test
    void hourDistributionAnalyzer() {
        TableResult table = new HourDistributionAnalyzer().doAnalysis(logs);
        assertThat(table.getRows())
            .contains(List.of("8:00 - 9:00", "1"))
            .contains(List.of("9:00 - 10:00", "1"))
            .contains(List.of("10:00 - 11:00", "1"));
    }
}
