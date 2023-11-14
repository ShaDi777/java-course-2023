package edu.project3.services.ReportSavers;

import edu.project3.models.TableResult;
import java.util.List;

public abstract class ReportSaver {
    public abstract void addTable(TableResult table);

    protected int[] getColumnSizes(List<String> titles, List<List<String>> rows) {
        int[] columnSizes = titles.stream().mapToInt(String::length).toArray();
        for (List<String> row : rows) {
            for (int i = 0; i < row.size() && i < columnSizes.length; i++) {
                columnSizes[i] = Math.max(columnSizes[i], row.get(i).length());
            }
        }

        // Add guaranteed indent from left and right
        for (int i = 0; i < columnSizes.length; i++) {
            columnSizes[i] += 2;
        }

        return columnSizes;
    }

    protected String buildRowWithSeparator(int[] columnSizes, List<String> row, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columnSizes.length && i < row.size(); i++) {
            int left = (columnSizes[i] - row.get(i).length()) / 2;
            int right = (columnSizes[i] - row.get(i).length()) - left;
            sb.append(separator);
            sb.append(" ".repeat(left));
            sb.append(row.get(i));
            sb.append(" ".repeat(right));
        }
        sb.append(separator);
        return sb.toString();
    }
}
