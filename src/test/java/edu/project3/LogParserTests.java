package edu.project3;

import edu.project3.entities.Log;
import edu.project3.models.HttpMethod;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogParserTests {
    @Test
    void logWithAllInfo() {
        String logString = "93.180.71.3 - 127.0.0.1 [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 777 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";

        Log log = Log.parse(logString);

        assertThat(log.remoteAddress()).isEqualTo("93.180.71.3");
        assertThat(log.remoteUser()).isEqualTo("127.0.0.1");
        assertThat(log.timeLocal()).isEqualTo(OffsetDateTime.of(2015, 5, 17, 8, 5, 32, 0, ZoneOffset.UTC));
        assertThat(log.method()).isEqualTo(HttpMethod.GET);
        assertThat(log.resource()).isEqualTo("/downloads/product_1");
        assertThat(log.request()).isEqualTo("GET /downloads/product_1 HTTP/1.1");
        assertThat(log.status()).isEqualTo(200);
        assertThat(log.bodyBytesSent()).isEqualTo(777);
        assertThat(log.httpReferer()).isEqualTo("-");
        assertThat(log.httpUserAgent()).isEqualTo("Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)");
    }

    @Test
    void wrongFormatLog() {
        // No [] around date
        String logString = "1 - 1 17/May/2015:08:05:32 +0000 \"GET /download HTTP/1.1\" 200 0 \"-\" \"Debian APT-HTTP/1.3\"";
        assertThrows(IllegalArgumentException.class, () -> Log.parse(logString));
    }
}
