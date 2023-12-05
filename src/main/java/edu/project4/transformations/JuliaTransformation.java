package edu.project4.transformations;

import edu.project4.models.Point;

public class JuliaTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double rRoot = Math.sqrt(point.getR());

        return new Point(
            rRoot * Math.cos(point.getTheta() / 2),
            rRoot * Math.sin(point.getTheta() / 2)
        );
    }
}
