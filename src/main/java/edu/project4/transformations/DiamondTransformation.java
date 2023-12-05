package edu.project4.transformations;

import edu.project4.models.Point;

public class DiamondTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            Math.sin(point.getTheta()) * Math.cos(point.getR()),
            Math.cos(point.getTheta()) * Math.sin(point.getR())
        );
    }
}
