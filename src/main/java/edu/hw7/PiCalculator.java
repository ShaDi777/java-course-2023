package edu.hw7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;

public final class PiCalculator {
    private static final double CIRCLE_CENTER = 0.5;
    private static final double CIRCLE_RADIUS = 0.5;
    private static final int MONTE_CARLO_CONST = 4;

    private PiCalculator() {
    }

    public static double singleThread(int totalDots) {
        int circleCount = simulate(totalDots);
        return (double) MONTE_CARLO_CONST * circleCount / totalDots;
    }

    public static double multiThread(int totalDots, int threadAmount) {
        List<SimulatorThread> threads = new ArrayList<>();
        for (int i = 0; i < threadAmount - 1; i++) {
            threads.add(new SimulatorThread(totalDots / threadAmount));
            threads.getLast().start();
        }
        threads.add(new SimulatorThread(totalDots / threadAmount + totalDots % threadAmount));
        threads.getLast().start();

        int totalCircleCount = 0;
        try {
            for (SimulatorThread thread : threads) {
                thread.join();
                totalCircleCount += thread.totalCircleCount;
            }
        } catch (InterruptedException e) {
            LogManager.getLogger().error(e);
        }

        return (double) MONTE_CARLO_CONST * totalCircleCount / totalDots;
    }

    public double multiThread(int totalDots) {
        return multiThread(totalDots, 2);
    }

    private static int simulate(int totalDots) {
        Random random = new Random();
        int circleCount = 0;
        for (int i = 0; i < totalDots; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (Math.sqrt(
                (CIRCLE_CENTER - x) * (CIRCLE_CENTER - x) + (CIRCLE_CENTER - y) * (CIRCLE_CENTER - y)
            ) <= CIRCLE_RADIUS
            ) {
                circleCount++;
            }
        }

        return circleCount;
    }

    private static class SimulatorThread extends Thread {
        private final int totalDots;
        private int totalCircleCount;

        SimulatorThread(int totalDots) {
            this.totalDots = totalDots;
        }

        @Override
        public void run() {
            totalCircleCount += simulate(totalDots);
        }
    }
}
