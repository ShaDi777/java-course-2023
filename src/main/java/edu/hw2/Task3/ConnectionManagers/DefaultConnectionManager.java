package edu.hw2.Task3.ConnectionManagers;

import edu.hw2.Task3.Connections.Connection;
import edu.hw2.Task3.Connections.FaultyConnection;
import edu.hw2.Task3.Connections.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final double errorRate;
    private final int faultyConnectionErrorFreq;

    public DefaultConnectionManager(double errorRate, int faultyConnectionErrorFreq) {
        if (errorRate > 1 || errorRate < 0) {
            throw new IllegalArgumentException();
        }

        this.errorRate = errorRate;
        this.faultyConnectionErrorFreq = faultyConnectionErrorFreq;
    }

    @Override
    public Connection getConnection() {
        return new Random().nextDouble() >= errorRate
                ? new FaultyConnection(faultyConnectionErrorFreq)
                : new StableConnection();
    }
}
