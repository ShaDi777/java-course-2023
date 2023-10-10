package edu.hw2.Task3.Connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.trace("Successfully executed: {}", command);
    }

    @Override
    public void close() throws Exception {
        LOGGER.trace("StableConnection closed!");
    }
}
