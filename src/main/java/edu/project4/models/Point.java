package edu.project4.models;

public record Point(double x, double y) {
    public double getR() {
        return Math.sqrt(x * x + y * y);
    }

    public double getTheta() {
        return Math.atan(x / y);
    }
}
