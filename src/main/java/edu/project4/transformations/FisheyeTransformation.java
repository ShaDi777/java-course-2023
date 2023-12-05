package edu.project4.transformations;

import edu.project4.models.Point;

public class FisheyeTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double coef = 2 / (point.getR());

        return new Point(
            coef * point.y(),
            coef * point.x()
        );
    }
}
