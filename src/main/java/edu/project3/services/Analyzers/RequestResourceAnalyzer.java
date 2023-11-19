package edu.project3.services.Analyzers;

import edu.project3.entities.Log;
import edu.project3.models.TableResult;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RequestResourceAnalyzer implements Analyzer {
    @Override
    public TableResult doAnalysis(List<Log> logs) {
        TableResult tableResult = new TableResult("Requested resources", List.of("Resource", "Amount"));

        Map<String, Integer> logGroups = logs.stream()
            .filter(Objects::nonNull)
            .collect(
                Collectors.toMap(
                    Log::resource,
                    (log -> 1),
                    Integer::sum,
                    TreeMap::new
                )
            );

        for (var resourceWithAmount : logGroups.entrySet()) {
            tableResult.addRow(
                List.of(
                    resourceWithAmount.getKey(),
                    resourceWithAmount.getValue().toString()
                )
            );
        }

        return tableResult;
    }
}
