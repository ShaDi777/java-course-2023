package edu.project4.transformations;

import edu.project4.models.Point;

public class ExTransformation implements Transformation {
    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public Point apply(Point point) {
        double r = point.getR();
        double p0 = Math.sin(point.getTheta() + r);
        double p1 = Math.cos(point.getTheta() - r);

        return new Point(
            r * (Math.pow(p0, 3) + Math.pow(p1, 3)),
            r * (Math.pow(p0, 3) - Math.pow(p1, 3))
        );
    }
}
