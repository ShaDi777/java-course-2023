package edu.hw2.Task2;

public class Square extends Rectangle {
    public Square() {}

    public Square(int sideSize) {
        super(sideSize, sideSize);
    }

    public final Square setSideSize(int size) {
        return new Square(size);
    }
}
