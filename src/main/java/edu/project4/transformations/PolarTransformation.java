package edu.project4.transformations;

import edu.project4.models.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(point.getTheta() / Math.PI, point.getR() - 1);
    }
}
