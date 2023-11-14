package edu.project3.services.Analyzers;

import edu.project3.entities.Log;
import edu.project3.models.TableResult;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HourDistributionAnalyzer implements Analyzer {
    @Override
    public TableResult doAnalysis(List<Log> logs) {
        TableResult tableResult = new TableResult("Hour distribution", List.of("Hour", "Amount"));

        Map<Integer, Integer> logGroups =
            logs.stream().collect(Collectors.toMap(log -> log.timeLocal().getHour(), (log -> 1), Integer::sum));

        for (var hourWithAmount : logGroups.entrySet()
                                    .stream()
                                    .sorted(Comparator.comparingInt(Map.Entry::getKey))
                                    .toList()) {
                                    tableResult.addRow(
                List.of(
                    hourWithAmount.getKey().toString() + ":00 - " + (hourWithAmount.getKey() + 1) + ":00",
                    hourWithAmount.getValue().toString()
                )
            );
        }

        return tableResult;
    }
}
