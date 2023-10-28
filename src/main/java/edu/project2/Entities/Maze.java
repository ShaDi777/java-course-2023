package edu.project2.Entities;

import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import java.util.List;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;
    private final Coordinate start;
    private final Coordinate end;

    public static final List<Coordinate> DELTA_LIST = List.of(
        new Coordinate(-1, 0),
        new Coordinate(+1, 0),
        new Coordinate(0, -1),
        new Coordinate(0, +1)
    );

    public Maze(Cell[][] grid, Coordinate start, Coordinate end) {
        this.height = grid.length;
        this.width = grid[0].length;
        this.grid = grid;
        this.start = start;
        this.end = end;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }
}
