package edu.project4.transformations;

import edu.project4.models.Point;

public class CrossTransformation implements Transformation {
    @Override public Point apply(Point point) {
        double coef = Math.sqrt(1. / Math.pow(point.x() * point.x() - point.y() * point.y(), 2));

        return new Point(
            coef * point.x(),
            coef * point.y()
        );
    }
}
