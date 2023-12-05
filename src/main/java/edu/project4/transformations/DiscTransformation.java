package edu.project4.transformations;

import edu.project4.models.Point;

public class DiscTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double coef = point.getTheta() / Math.PI;

        return new Point(
            coef * Math.sin(Math.PI * point.getR()),
            coef * Math.cos(Math.PI * point.getR())
        );
    }
}
