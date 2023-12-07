package edu.project4.transformations;

import edu.project4.models.Point;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = point.getR();
        double theta = point.getTheta();

        return new Point(
            r * Math.sin(theta * r),
            -r * Math.cos(theta * r)
        );
    }
}
