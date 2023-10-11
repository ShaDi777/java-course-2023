package edu.hw2.Task3.ConnectionManagers;

import edu.hw2.Task3.Connections.Connection;
import edu.hw2.Task3.Connections.FaultyConnection;

public class FaultyConnectionManager implements ConnectionManager {
    private final int errorFreq;

    public FaultyConnectionManager(int errorFreq) {
        this.errorFreq = errorFreq;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(errorFreq);
    }
}
