package edu.project3.services.Analyzers;

import edu.project3.entities.Log;
import edu.project3.models.TableResult;
import java.util.List;

public interface Analyzer {
    TableResult doAnalysis(List<Log> logs);
}
