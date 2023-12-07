package edu.project4.transformations;

import edu.project4.models.Point;

public class ExponentialTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double coef = Math.exp(point.x() - 1);

        return new Point(
            coef * Math.cos(Math.PI * point.y()),
            coef * Math.sin(Math.PI * point.y())
        );
    }
}
