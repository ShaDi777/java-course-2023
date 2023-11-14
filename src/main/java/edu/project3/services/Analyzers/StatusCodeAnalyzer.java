package edu.project3.services.Analyzers;

import edu.project3.entities.Log;
import edu.project3.models.TableResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatusCodeAnalyzer implements Analyzer {
    private static final Map<Integer, String> DESCRIPTION = Map.<Integer, String>ofEntries(
        Map.entry(100, "Continue"),
        Map.entry(101, "Switching Protocols"),
        Map.entry(102, "Processing"),
        Map.entry(103, "Early Hints"),
        Map.entry(200, "Ok"),
        Map.entry(201, "Created"),
        Map.entry(202, "Accepted"),
        Map.entry(203, "Non-Authoritative Information"),
        Map.entry(204, "No Content"),
        Map.entry(205, "Reset Content"),
        Map.entry(206, "Partial Content"),
        Map.entry(207, "Multi-Status"),
        Map.entry(208, "Already Reported"),
        Map.entry(226, "IM Used"),
        Map.entry(300, "Multiple Choices"),
        Map.entry(301, "Moved Permanently"),
        Map.entry(302, "Moved Temporarily"),
        Map.entry(303, "See Other"),
        Map.entry(304, "Not Modified"),
        Map.entry(305, "Use Proxy"),
        Map.entry(307, "Temporary Redirect"),
        Map.entry(308, "Permanent Redirect"),
        Map.entry(400, "Bad Request"),
        Map.entry(401, "Unauthorized"),
        Map.entry(402, "Payment Required"),
        Map.entry(403, "Forbidden"),
        Map.entry(404, "Not Found"),
        Map.entry(405, "Method Not Allowed"),
        Map.entry(406, "Not Acceptable"),
        Map.entry(407, "Proxy Authentication Required"),
        Map.entry(408, "Request Timeout"),
        Map.entry(409, "Conflict"),
        Map.entry(410, "Gone"),
        Map.entry(411, "Length Required"),
        Map.entry(412, "Precondition Failed"),
        Map.entry(413, "Payload Too Large"),
        Map.entry(414, "URI Too Long"),
        Map.entry(415, "Unsupported Media Type"),
        Map.entry(416, "Range Not Satisfiable"),
        Map.entry(417, "Expectation Failed"),
        Map.entry(418, "I’m a teapot"),
        Map.entry(419, "Authentication Timeout"),
        Map.entry(421, "Misdirected Request"),
        Map.entry(422, "Unprocessable Entity"),
        Map.entry(423, "Locked"),
        Map.entry(424, "Failed Dependency"),
        Map.entry(425, "Too Early"),
        Map.entry(426, "Upgrade Required"),
        Map.entry(428, "Precondition Required"),
        Map.entry(429, "Too Many Requests"),
        Map.entry(431, "Request Header Fields Too Large"),
        Map.entry(449, "Retry With"),
        Map.entry(451, "Unavailable For Legal Reasons"),
        Map.entry(499, "Client Closed Request"),
        Map.entry(500, "Internal Server Error"),
        Map.entry(501, "Not Implemented"),
        Map.entry(502, "Bad Gateway"),
        Map.entry(503, "Service Unavailable"),
        Map.entry(504, "Gateway Timeout"),
        Map.entry(505, "HTTP Version Not Supported"),
        Map.entry(506, "Variant Also Negotiates"),
        Map.entry(507, "Insufficient Storage"),
        Map.entry(508, "Loop Detected"),
        Map.entry(509, "Bandwidth Limit Exceeded"),
        Map.entry(510, "Not Extended"),
        Map.entry(511, "Network Authentication Required"),
        Map.entry(520, "Unknown Error"),
        Map.entry(521, "Web Server Is Down"),
        Map.entry(522, "Connection Timed Out"),
        Map.entry(523, "Origin Is Unreachable"),
        Map.entry(524, "A Timeout Occurred"),
        Map.entry(525, "SSL Handshake Failed"),
        Map.entry(526, "Invalid SSL Certificate")
    );

    @Override
    public TableResult doAnalysis(List<Log> logs) {
        TableResult tableResult = new TableResult("Status responses", List.of("Code", "Name", "Amount"));
        Map<Integer, Integer> logGroups =
            logs.stream().collect(Collectors.toMap(Log::status, (log -> 1), Integer::sum));
        for (var statusWithAmount : logGroups.entrySet()
                                    .stream()
                                    .sorted((kv1, kv2) -> kv2.getValue() - kv1.getValue())
                                    .toList()) {
            tableResult.addRow(
                List.of(
                    statusWithAmount.getKey().toString(),
                    getDescription(statusWithAmount.getKey()),
                    statusWithAmount.getValue().toString()
                )
            );
        }

        return tableResult;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private String getDescription(int status) {
        String description = DESCRIPTION.getOrDefault(status, null);
        if (description == null) {
            description = switch (status % 100) {
                case 1 -> "Informational";
                case 2 -> "Success";
                case 3 -> "Redirection";
                case 4 -> "Client Error";
                case 5 -> "Server Error";
                default -> "Unknown";
            };
        }

        return description;
    }
}
