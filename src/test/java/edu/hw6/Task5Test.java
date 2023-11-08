package edu.hw6;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Task5Test {
    private final HttpClient client = mock(HttpClient.class);
    private final HttpResponse response = mock(HttpResponse.class);

    @Test
    void testTopNewsRequest() throws IOException, InterruptedException {
        long[] expectedResult = {1, 2, 3};
        when(response.body()).thenReturn("[1,2,3]");
        when(client.send(any(), any())).thenReturn(response);

        HackerNews hackerNews = new HackerNews(client);
        long[] topNewsId = hackerNews.hackerNewsTopStories();

        //then:
        assertThat(topNewsId).isEqualTo(expectedResult);
    }

    @Test
    void testNewsInfo() throws IOException, InterruptedException {
        String expectedResult = "JDK 21 Release Notes";
        when(response.body()).thenReturn(
            "{\"by\":\"mfiguiere\"," +
                "\"descendants\":327," +
                "\"id\":1," +
                "\"kids\":[37570620]," +
                "\"score\":246," +
                "\"time\":1695132006," +
                "\"title\":\"JDK 21 Release Notes\"," +
                "\"type\":\"story\"," +
                "\"url\":\"https://jdk.java.net/21/release-notes\"}");
        when(client.send(any(), any())).thenReturn(response);

        HackerNews hackerNews = new HackerNews(client);
        String topNewsId = hackerNews.news(1);

        //then:
        assertThat(topNewsId).isEqualTo(expectedResult);
    }

    @Test
    void testNoSuchIdNews() throws IOException, InterruptedException {
        String expectedResult = "";
        when(response.body()).thenReturn("null");
        when(client.send(any(), any())).thenReturn(response);

        HackerNews hackerNews = new HackerNews(client);
        String topNewsId = hackerNews.news(999);

        //then:
        assertThat(topNewsId).isEqualTo(expectedResult);
    }
}
