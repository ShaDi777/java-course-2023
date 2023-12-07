package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StatsCollector {
    private static final int TIMEOUT_MINUTES = 2;

    private final ConcurrentMap<String, Stats> collectedStats = new ConcurrentHashMap<>();
    private final ExecutorService threadPool;

    public StatsCollector(int numThreads) {
        threadPool = Executors.newFixedThreadPool(
            numThreads > 0
                ? numThreads
                : Runtime.getRuntime().availableProcessors()
        );
    }

    public Map<String, Stats> getStats() {
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(TIMEOUT_MINUTES, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return collectedStats;
    }

    public void push(String metricName, double[] data) {
        if (threadPool.isTerminated()) {
            throw new RuntimeException("New metrics can not be submitted");
        }
        threadPool.execute(() -> writeStats(metricName, data));
    }

    private void writeStats(String metricName, double[] data) {
        collectedStats.put(metricName, processData(data));
    }

    private Stats processData(double[] data) {
        double sum = 0;
        double count = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (double d : data) {
            sum += d;
            count++;
            min = Math.min(min, d);
            max = Math.max(max, d);
        }

        return new Stats(sum, sum / count, min, max);
    }
}
