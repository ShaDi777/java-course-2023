package edu.project3.services.Analyzers;

import edu.project3.entities.Log;
import edu.project3.models.HttpMethod;
import edu.project3.models.TableResult;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MethodAnalyzer implements Analyzer {
    @Override
    public TableResult doAnalysis(List<Log> logs) {
        TableResult tableResult = new TableResult("Http methods", List.of("Method", "Amount"));

        TreeMap<HttpMethod, Integer> logGroups = logs.stream()
            .filter(Objects::nonNull)
            .collect(
                Collectors.toMap(
                    Log::method,
                    (log -> 1),
                    Integer::sum,
                    TreeMap::new
                )
            );

        for (var methodWithAmount : logGroups.entrySet()) {
            tableResult.addRow(
                List.of(
                    methodWithAmount.getKey().toString(),
                    methodWithAmount.getValue().toString()
                )
            );
        }

        return tableResult;
    }
}
