package edu.project2.Services.Solvers;

import edu.project2.Entities.Maze;
import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;

public abstract class BaseSolver implements Solver {
    protected boolean isValidPosition(int row, int col, int height, int width) {
        return col >= 0 && row >= 0 && row < height && col < width;
    }

    protected boolean isPassageCoordinate(Maze maze, Coordinate coordinate) {
        return isValidPosition(coordinate.row(), coordinate.col(), maze.getHeight(), maze.getWidth())
            && maze.getGrid()[coordinate.row()][coordinate.col()].type() == Cell.Type.PASSAGE;
    }

    protected boolean isInvalidEndpoints(Maze maze, Coordinate start, Coordinate end) {
        return !isPassageCoordinate(maze, start) || !isPassageCoordinate(maze, end);
    }
}
