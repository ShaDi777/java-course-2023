package edu.project3.services.Analyzers;

import edu.project3.entities.Log;
import edu.project3.models.HttpMethod;
import edu.project3.models.TableResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MethodAnalyzer implements Analyzer {
    @Override
    public TableResult doAnalysis(List<Log> logs) {
        TableResult tableResult = new TableResult("Http methods", List.of("Method", "Amount"));

        Map<HttpMethod, Integer> logGroups =
            logs.stream().collect(Collectors.toMap(Log::method, (log -> 1), Integer::sum));

        for (var methodWithAmount : logGroups.entrySet()
                                    .stream()
                                    .sorted((kv1, kv2) -> kv2.getValue() - kv1.getValue())
                                    .toList()) {
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
