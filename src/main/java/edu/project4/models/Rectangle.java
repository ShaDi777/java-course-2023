package edu.project4.models;

import java.util.concurrent.ThreadLocalRandom;

public record Rectangle(double x, double y, double width, double height) {
    public boolean contains(Point p) {
        return p.x() >= x && p.x() < width + x && p.y() >= y && p.y() < height + y;
    }

    public Point getRandomPoint() {
        double px = ThreadLocalRandom.current().nextDouble(0, width);
        double py = ThreadLocalRandom.current().nextDouble(0, height);
        return new Point(px, py);
    }
}
