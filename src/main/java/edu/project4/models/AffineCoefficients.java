package edu.project4.models;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public record AffineCoefficients(double a, double b, double c, double d, double e, double f, Color color) {
    private static final int MAX_TRY_COUNT = 100_000;
    private static final int COLOR_BOUND = 256;

    public static AffineCoefficients create() {
        int tryCount = 0;
        while (tryCount++ < MAX_TRY_COUNT) {
            double a = ThreadLocalRandom.current().nextDouble(-1, 1);
            double b = ThreadLocalRandom.current().nextDouble(-1, 1);
            double c = ThreadLocalRandom.current().nextDouble(-1, 1);
            double d = ThreadLocalRandom.current().nextDouble(-1, 1);
            double e = ThreadLocalRandom.current().nextDouble(-1, 1);
            double f = ThreadLocalRandom.current().nextDouble(-1, 1);
            if (isCoefficientValid(a, b, c, d, e, f)) {
                return new AffineCoefficients(a, b, c, d, e, f, getRandomColor());
            }
        }
        return new AffineCoefficients(0, 0, 0, 0, 0, 0, getRandomColor());
    }

    private static boolean isCoefficientValid(double a, double b, double c, double d, double e, double f) {
        return (a * a + d * d < 1)
            && (b * b + e * e < 1)
            && (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d));
    }

    private static Color getRandomColor() {
        return new Color(
            ThreadLocalRandom.current().nextInt(0, COLOR_BOUND),
            ThreadLocalRandom.current().nextInt(0, COLOR_BOUND),
            ThreadLocalRandom.current().nextInt(0, COLOR_BOUND)
        );
    }
}
