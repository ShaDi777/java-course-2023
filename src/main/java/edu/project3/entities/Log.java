package edu.project3.entities;

import edu.project3.models.HttpMethod;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Log(
    String remoteAddress,
    String remoteUser,
    OffsetDateTime timeLocal,
    HttpMethod method,
    String request,
    int status,
    String resource,
    long bodyBytesSent,
    String httpReferer,
    String httpUserAgent
) {
    private final static Pattern LOG_REGEX = Pattern.compile(
            "(-|\\d+\\.\\d+\\.\\d+\\.\\d+) - "
                + "(-|\\d+\\.\\d+\\.\\d+\\.\\d+) "
                + "\\[(\\d{2}/[A-Z][a-z]{2}/\\d{4}:\\d{2}:\\d{2}:\\d{2} .+)] "
                + "\"(.+?)\" (\\d+) (\\d+) \"(.+?)\" \"(.+?)\""
        );

    @SuppressWarnings("checkstyle:MagicNumber")
    public static Log parse(String log) {
        Matcher matcher = LOG_REGEX.matcher(log);
        if (matcher.matches()) {
            String remoteAddress = matcher.group(1);
            String remoteUser = matcher.group(2);
            OffsetDateTime timeLocal = parseOffsetDate(matcher.group(3));
            String request = matcher.group(4);
            String[] requestSplit = request.split(" ");
            HttpMethod method = HttpMethod.valueOf(requestSplit[0]);
            String resource = requestSplit[1];
            int status = Integer.parseInt(matcher.group(5));
            long bodyBytesSent = Long.parseLong(matcher.group(6));
            String httpReferer = matcher.group(7);
            String httpUserAgent = matcher.group(8);
            return new Log(
                remoteAddress,
                remoteUser,
                timeLocal,
                method,
                request,
                status,
                resource,
                bodyBytesSent,
                httpReferer,
                httpUserAgent
            );
        } else {
            throw new IllegalArgumentException("Log does not match pattern");
        }
    }

    @SuppressWarnings("checkstyle:ParameterAssignment")
    private static OffsetDateTime parseOffsetDate(String dateString) {
        dateString = dateString.replaceFirst("Jan", "01");
        dateString = dateString.replaceFirst("Feb", "02");
        dateString = dateString.replaceFirst("Mar", "03");
        dateString = dateString.replaceFirst("Apr", "04");
        dateString = dateString.replaceFirst("May", "05");
        dateString = dateString.replaceFirst("Jun", "06");
        dateString = dateString.replaceFirst("Jul", "07");
        dateString = dateString.replaceFirst("Aug", "08");
        dateString = dateString.replaceFirst("Sep", "09");
        dateString = dateString.replaceFirst("Oct", "10");
        dateString = dateString.replaceFirst("Nov", "11");
        dateString = dateString.replaceFirst("Dec", "12");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss ");
        DateTimeFormatter dateOffsetFormat = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(dateFormat)
            .appendOffset("+HHMM", "+0000")
            .toFormatter();
        return OffsetDateTime.parse(dateString, dateOffsetFormat);
    }
}
