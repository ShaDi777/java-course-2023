package edu.hw8.Task1.server;

import edu.hw8.Task1.Configuration;
import edu.hw8.Task1.storage.QuoteStorage;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SimpleServer implements Server {
    private final QuoteStorage quoteStorage;
    private final ThreadPoolExecutor threadPool;
    private volatile boolean isRunning = false;

    public SimpleServer(int maxClients, QuoteStorage quoteStorage) {
        this.quoteStorage = quoteStorage;

        int maxClientsNormalized = Math.max(1, maxClients);
        maxClientsNormalized = Math.min(maxClientsNormalized, Runtime.getRuntime().availableProcessors());

        threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxClientsNormalized);
    }

    @Override
    public void run() {
        try {
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    private void start() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(Configuration.HOSTNAME, Configuration.PORT));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        Map<SelectionKey, Boolean> used = new ConcurrentHashMap<>();

        isRunning = true;
        while (isRunning) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();

                if (key.isAcceptable()) {
                    register(selector, serverSocket);
                }

                if (key.isReadable() && !used.getOrDefault(key, false)) {
                    used.put(key, true);
                    threadPool.execute(() -> {
                        try {
                            sendResponse(key);
                            used.put(key, false);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                iter.remove();
            }
        }

        serverSocket.close();
    }

    private void sendResponse(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(Configuration.MESSAGE_BYTE_SIZE);
        int r = client.read(buffer);
        if (r == -1) {
            client.close();
        } else {
            buffer.flip();
            String keyWord = new String(buffer.array()).trim();
            String quote = quoteStorage.findFirstByKeyWord(keyWord);
            if (quote.isBlank()) {
                quote = "NULL";
            }
            buffer.clear();
            buffer.put(quote.getBytes());
            buffer.flip();
            client.write(buffer);
        }
    }

    private void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }
}
