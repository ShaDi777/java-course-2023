package edu.hw2.Task3.Connections;

import edu.hw2.Task3.Exceptions.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int WITHOUT_ERRORS = 0;

    private final int errorFreq;
    private int attempts = 0;

    public FaultyConnection(int errorFreq) {
        this.errorFreq = Math.abs(errorFreq);
    }

    @Override
    public void execute(String command) {
        attempts++;
        if (errorFreq != WITHOUT_ERRORS && attempts % errorFreq == 0) {
            throw new ConnectionException("Could not execute: " + command);
        }

        LOGGER.trace("Successfully executed: {}", command);
    }

    @Override
    public void close() throws Exception {
        LOGGER.trace("FaultyConnection closed!");
    }
}
