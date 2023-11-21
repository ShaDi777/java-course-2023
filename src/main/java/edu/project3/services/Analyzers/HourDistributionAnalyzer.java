package edu.project3.services.Analyzers;

import edu.project3.entities.Log;
import edu.project3.models.TableResult;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class HourDistributionAnalyzer implements Analyzer {
    @Override
    public TableResult doAnalysis(List<Log> logs) {
        TableResult tableResult = new TableResult("Hour distribution", List.of("Hour", "Amount"));

        TreeMap<Integer, Integer> logGroups = logs.stream()
            .filter(Objects::nonNull)
            .collect(
                Collectors.toMap(
                    log -> log.timeLocal().getHour(),
                    (log -> 1),
                    Integer::sum,
                    TreeMap::new
                )
            );

        for (var hourWithAmount : logGroups.entrySet()) {
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
