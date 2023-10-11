package edu.hw2.Task3;

import edu.hw2.Task3.ConnectionManagers.ConnectionManager;
import edu.hw2.Task3.Exceptions.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final static Logger LOGGER = LogManager.getLogger();

    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        for (int i = 1; i <= maxAttempts; i++) {
            try (var connection = manager.getConnection()) {
                connection.execute(command);
                break;
            } catch (ConnectionException e) {
                if (i == maxAttempts) {
                    throw new ConnectionException("Could not execute: " + command, e);
                }
            } catch (Exception e) {
                LOGGER.trace("Error while closing connection!", e);
            }
        }
    }
}
