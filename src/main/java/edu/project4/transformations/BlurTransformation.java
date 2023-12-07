package edu.project4.transformations;

import edu.project4.models.Point;
import java.util.concurrent.ThreadLocalRandom;

public class BlurTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double psi1 = ThreadLocalRandom.current().nextDouble();
        double psi2 = ThreadLocalRandom.current().nextDouble();

        return new Point(
            psi1 * Math.cos(2 * Math.PI * psi2),
            psi1 * Math.sin(2 * Math.PI * psi2)
        );
    }
}
