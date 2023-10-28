package edu.project2.Services.Generators;

import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import java.util.Random;

public abstract class BaseGenerator implements Generator {
    protected static final int MIN_SIZE = 3;

    protected void validateSize(int height, int width) {
        if (height <= MIN_SIZE || width <= MIN_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    protected boolean isInsideMaze(Coordinate coordinate, int height, int width) {
        return coordinate.row() >= 1
            && coordinate.col() >= 1
            && coordinate.row() < height - 1
            && coordinate.col() < width - 1;
    }

    protected Cell[][] generateEmpty(int height, int width) {
        validateSize(height, width);

        Cell[][] maze = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = new Cell(i, j, Cell.Type.WALL);
            }
        }
        return maze;
    }

    protected Coordinate getRandomStart(int height, int width) {
        Random random = new Random();
        return new Coordinate(random.nextInt(height - 2) + 1, 0);
    }

    protected Coordinate getRandomEnd(Cell[][] maze) {
        Random random = new Random();
        int firstPosition = random.nextInt(maze.length - 2) + 1;
        for (int i = firstPosition; i < firstPosition + maze.length; i++) {
            int pos = i % maze.length;
            if (maze[pos][maze[pos].length - 2].type() == Cell.Type.PASSAGE) {
                return new Coordinate(pos, maze[pos].length - 1);
            }
        }
        return null;
    }
}
