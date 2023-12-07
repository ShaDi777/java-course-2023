package edu.project4.transformations;

import edu.project4.models.Point;

public class SpiralTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = point.getR();
        double coef = 1. / r;

        return new Point(
            coef * (Math.cos(point.getTheta()) + Math.sin(r)),
            coef * (Math.sin(point.getTheta()) - Math.cos(r))
        );
    }
}
