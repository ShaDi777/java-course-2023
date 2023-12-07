package edu.project4.transformations;

import edu.project4.models.Point;
import java.util.concurrent.ThreadLocalRandom;

public class PowerTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        boolean minus = ThreadLocalRandom.current().nextBoolean();

        double coef = Math.pow(point.getR(), Math.sin(point.getTheta()));

        return new Point(
            (minus ? -1 : 1) * coef * Math.cos(point.getTheta()),
            coef * Math.sin(point.getTheta())
        );
    }
}
