package edu.hw2.Task2;

public class Rectangle {
    public static Builder builder = new Builder();
    private final int width;
    private final int height;

    public Rectangle() {
        this.width = 0;
        this.height = 0;
    }

    public Rectangle(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Negative size");
        }

        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }

    public static class Builder {
        private int width = 0;
        private int height = 0;

        public Builder withWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder withHeight(int height) {
            this.height = height;
            return this;
        }

        public Rectangle build() {
            Rectangle result = new Rectangle(width, height);
            width = 0;
            height = 0;
            return result;
        }
    }
}
