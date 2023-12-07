package edu.hw8.Task1.client;

import edu.hw8.Task1.Configuration;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleClient implements Client {
    private static final Logger LOGGER = LogManager.getLogger();

    private SocketChannel client;
    private ByteBuffer buffer;

    public SimpleClient() {
    }

    @Override
    public void start() {
        try {
            client = SocketChannel.open(new InetSocketAddress(Configuration.HOSTNAME, Configuration.PORT));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        buffer = ByteBuffer.allocate(Configuration.MESSAGE_BYTE_SIZE);
    }

    @Override
    public void stop() {
        try {
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        buffer = null;
    }

    @Override
    public String sendMessage(String message) {
        buffer = ByteBuffer.allocate(Configuration.MESSAGE_BYTE_SIZE);
        buffer.put(message.getBytes());
        String response = null;
        try {
            buffer.flip();
            client.write(buffer);
            buffer.flip();
            buffer.clear();
            client.read(buffer);
            buffer.flip();
            byte[] slice = new byte[buffer.limit()];
            buffer.get(0, slice, 0, buffer.limit());
            response = new String(slice).trim();
            LOGGER.info("Response from server: " + response);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        return response;
    }
}
