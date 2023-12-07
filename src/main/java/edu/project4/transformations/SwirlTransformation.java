package edu.project4.transformations;

import edu.project4.models.Point;

public class SwirlTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = point.getR();
        double sinR2 = Math.sin(r * r);
        double cosR2 = Math.cos(r * r);

        return new Point(
            point.x() * sinR2 - point.y() * cosR2,
            point.x() * cosR2 + point.y() * sinR2
        );
    }
}
