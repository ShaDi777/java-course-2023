package edu.hw8.Task1.client;

public interface Client {
    void start();

    String sendMessage(String message);

    void stop();
}
