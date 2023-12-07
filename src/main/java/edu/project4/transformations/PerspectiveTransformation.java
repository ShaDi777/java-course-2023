package edu.project4.transformations;

import edu.project4.models.Point;

public class PerspectiveTransformation implements Transformation {
    private final double p1;
    private final double p2;

    public PerspectiveTransformation(double angle, double dist) {
        this.p1 = angle;
        this.p2 = dist;
    }

    @Override
    public Point apply(Point point) {
        double coef = p2 / (p2 - point.y() * Math.sin(p1));

        return new Point(
            coef * point.x(),
            coef * point.y() * Math.cos(p1)
        );
    }
}
