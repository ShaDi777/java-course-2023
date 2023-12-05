package edu.project4.transformations;

import edu.project4.models.Point;

public class BentTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        if (x >= 0) {
            if (y >= 0) {
                return new Point(x, y);
            } else {
                return new Point(x, y / 2);
            }
        } else {
            if (y >= 0) {
                return new Point(2 * x, y);
            } else {
                return new Point(2 * x, y / 2);
            }
        }
    }
}
