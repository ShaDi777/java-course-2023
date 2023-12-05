package edu.project4.transformations;

import edu.project4.models.Point;

public class HyperbolicTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            Math.sin(point.getTheta()) / point.getR(),
            point.getR() * Math.cos(point.getTheta())
        );
    }
}
