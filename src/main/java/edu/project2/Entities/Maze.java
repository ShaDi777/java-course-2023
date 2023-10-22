package edu.project2.Entities;

import edu.project2.Models.Cell;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(Cell[][] grid) {
        this.height = grid.length;
        this.width = grid[0].length;
        this.grid = grid;
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
}
