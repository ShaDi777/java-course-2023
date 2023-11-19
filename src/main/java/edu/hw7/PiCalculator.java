package edu.hw7;

import java.util.Random;

public final class PiCalculator {
    private static final Random RANDOM = new Random();

    private PiCalculator() {
    }

    public static double singleThread(int totalDots) {
        int circleCount = 0;
        for (int i = 0; i < totalDots; i++) {
            double x = RANDOM.nextDouble();
            double y = RANDOM.nextDouble();
        }
        return 0;
    }

    public static double multiThread(int totalDots, int threadAmount) {

    }

    public static double multiThread(int totalDots) {
        return multiThread(totalDots, 4);
    }
}
