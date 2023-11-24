package edu.hw8;

import edu.hw8.Task1.client.SimpleClient;
import edu.hw8.Task1.server.SimpleServer;
import edu.hw8.Task1.storage.QuoteStorage;
import edu.hw8.Task1.storage.SimpleQuoteStorage;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ClientServerTest {
    private static final QuoteStorage storage = new SimpleQuoteStorage();

    @Test
    void connectivityCheck() throws InterruptedException {
        AtomicReference<AssertionError> exception = new AtomicReference<>(null);

        SimpleServer server = new SimpleServer(5, storage);
        new Thread(server, "SERVER_CHECK").start();

        Thread client1 = new Thread(() -> {
            try {
                runClient();
            } catch (AssertionError e) {
                exception.set(e);
            }
        });
        client1.start();

        Thread client2 = new Thread(() -> {
            try {
                runClient();
            } catch (AssertionError e) {
                exception.set(e);
            }
        });
        client2.start();

        Thread client3 = new Thread(() -> {
            try {
                runClient();
            } catch (AssertionError e) {
                exception.set(e);
            }
        });
        client3.start();

        client1.join();
        client2.join();
        client3.join();

        server.stop();

        if (exception.get() != null) {
            throw exception.get();
        }
    }

    private void runClient() {
        SimpleClient client = new SimpleClient();

        client.start();
        String keyWord1 = "личности";
        String response1 = client.sendMessage(keyWord1);

        String keyWord2 = "глупый";
        String response2 = client.sendMessage(keyWord2);

        String keyWord3 = "интеллект";
        String response3 = client.sendMessage(keyWord3);

        String keyWord4 = "оскорбления";
        String response4 = client.sendMessage(keyWord4);

        String keyWordNotExist = "abobaaboba";
        String response5 = client.sendMessage(keyWordNotExist);

        assertThat(response1).isEqualTo(storage.findFirstByKeyWord(keyWord1));
        assertThat(response2).isEqualTo(storage.findFirstByKeyWord(keyWord2));
        assertThat(response3).isEqualTo(storage.findFirstByKeyWord(keyWord3));
        assertThat(response4).isEqualTo(storage.findFirstByKeyWord(keyWord4));
        assertThat(response5).isEqualTo("NULL");

        client.stop();
    }
}
