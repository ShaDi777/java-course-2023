package edu.hw2;

import edu.hw2.Task3.ConnectionManagers.DefaultConnectionManager;
import edu.hw2.Task3.ConnectionManagers.FaultyConnectionManager;
import edu.hw2.Task3.Connections.FaultyConnection;
import edu.hw2.Task3.Connections.StableConnection;
import edu.hw2.Task3.Exceptions.ConnectionException;
import edu.hw2.Task3.PopularCommandExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    @Test
    void defaultManagerTest() {
        var success = new DefaultConnectionManager(1, 10).getConnection();
        var fault = new DefaultConnectionManager(0, 10).getConnection();

        assertThat(success).isInstanceOf(StableConnection.class);
        assertThat(fault).isInstanceOf(FaultyConnection.class);
    }

    @Test
    @DisplayName("Exceptions on every try")
    void executeWithFaultyManagerTest1() {
        var executor = new PopularCommandExecutor(new FaultyConnectionManager(1), 10);

        assertThrows(ConnectionException.class, executor::updatePackages);
    }

    @Test
    @DisplayName("Exceptions on every 11th try")
    void executeWithFaultyManagerTest2() {
        var executor = new PopularCommandExecutor(new FaultyConnectionManager(11), 10);

        assertDoesNotThrow(executor::updatePackages);
    }
}
