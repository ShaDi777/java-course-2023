package edu.hw2.Task2;

public class Square extends Rectangle {
    public static Builder builder = new Builder();

    public Square() {}

    public Square(int sideSize) {
        super(sideSize, sideSize);
    }

    public static class Builder {
        private int size = 0;

        public Builder withSideSize(int size) {
            this.size = size;
            return this;
        }

        public Square build() {
            Square result = new Square(size);
            size = 0;
            return result;
        }
    }
}
