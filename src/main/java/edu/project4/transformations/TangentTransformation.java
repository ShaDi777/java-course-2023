package edu.project4.transformations;

import edu.project4.models.Point;

public class TangentTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            Math.sin(point.x()) / Math.cos(point.y()),
            Math.tan(point.y())
        );
    }
}
