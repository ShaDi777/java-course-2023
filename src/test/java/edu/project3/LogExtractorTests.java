package edu.project3;

import edu.project3.entities.Log;
import edu.project3.models.Configuration;
import edu.project3.services.LogExtractor;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LogExtractorTests {
    @Test
    void extractFromSingleFile() {
        Configuration configuration = new Configuration("src/test/java/edu/project3/files/logs.txt");

        List<Log> logs = LogExtractor.extractFrom(configuration);

        assertThat(logs)
            .contains(Log.parse("0 - 0 [17/May/2015:08:05:32 +0000] \"GET /file1 HTTP/1.1\" 200 2 \"-\" \"-\""))
            .contains(Log.parse("0 - 0 [17/May/2015:09:05:32 +0000] \"POST /file2 HTTP/1.1\" 500 3 \"-\" \"-\""))
            .contains(Log.parse("0 - 0 [17/May/2015:10:05:32 +0000] \"HEAD /file1 HTTP/1.1\" 404 4 \"-\" \"-\""))
            .contains(Log.parse("93.180.71.3 - 127.0.0.1 [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 777 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""));
    }

    @Test
    void extractFromMultipleFiles() {
        Configuration configuration = new Configuration("src/test/java/edu/project3/files/*.txt");

        List<Log> logs = LogExtractor.extractFrom(configuration);

        assertThat(logs)
            .contains(Log.parse("0 - 0 [17/May/2015:08:05:32 +0000] \"GET /file1 HTTP/1.1\" 200 2 \"-\" \"-\""))
            .contains(Log.parse("0 - 0 [17/May/2015:09:05:32 +0000] \"POST /file2 HTTP/1.1\" 500 3 \"-\" \"-\""))
            .contains(Log.parse("0 - 0 [17/May/2015:10:05:32 +0000] \"HEAD /file1 HTTP/1.1\" 404 4 \"-\" \"-\""))
            .contains(Log.parse("93.180.71.3 - 127.0.0.1 [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 777 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""))
            .contains(Log.parse("217.168.17.5 - - [17/May/2015:08:05:12 +0000] \"GET /downloads/product_2 HTTP/1.1\" 200 3316 \"-\" \"-\""))
            .contains(Log.parse("80.91.33.133 - - [17/May/2015:08:05:59 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)\""));
    }
}
