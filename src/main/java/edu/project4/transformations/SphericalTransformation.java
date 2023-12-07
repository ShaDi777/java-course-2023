package edu.project4.transformations;

import edu.project4.models.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = point.getR();
        return new Point(point.x() / (r * r), point.y() / (r * r));
    }
}
