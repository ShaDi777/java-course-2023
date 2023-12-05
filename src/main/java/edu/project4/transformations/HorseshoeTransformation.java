package edu.project4.transformations;

import edu.project4.models.Point;

public class HorseshoeTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            (point.x() - point.y()) * (point.x() + point.y()) / point.getR(),
            2 * point.x() * point.y() / point.getR()
        );
    }
}
