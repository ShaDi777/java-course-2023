package edu.project4.transformations;

import edu.project4.models.Point;

public class HandkerchiefTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = point.getR();

        return new Point(
            r * Math.sin(point.getTheta() + r),
            r * Math.cos(point.getTheta() - r)
        );
    }
}
