package edu.project2.Services.Generators;

import edu.project2.Entities.Maze;
import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PrimGenerator implements Generator {
    private static final int MIN_SIZE = 3;

    private boolean validPosition(int row, int col, int height, int width) {
        return col >= 1 && row >= 1 && row < height - 1 && col < width - 1;
    }

    private int countBorders(Cell[][] maze, Coordinate current, List<Coordinate> deltaList) {
        int countPossibleBorders = 0;
        for (var delta : deltaList) {
            if (!validPosition(current.row() + delta.row(), current.col() + delta.col(), maze.length, maze[0].length)
                || maze[current.row() + delta.row()][current.col() + delta.col()].type() == Cell.Type.WALL) {
                countPossibleBorders++;
            }
        }

        return countPossibleBorders;
    }

    private void doMove(
        Cell[][] maze,
        List<Coordinate> borders,
        List<Coordinate> deltaList,
        Coordinate target
    ) {
        if (maze[target.row()][target.col()].type() == Cell.Type.PASSAGE
            || countBorders(maze, target, deltaList) < MIN_SIZE) {
            return;
        }

        maze[target.row()][target.col()] = new Cell(target.row(), target.col(), Cell.Type.PASSAGE);
        for (var delta : deltaList) {
            if (validPosition(target.row() + delta.row(), target.col() + delta.col(), maze.length, maze[0].length)
                && maze[target.row() + delta.row()][target.col() + delta.col()].type() == Cell.Type.WALL) {
                borders.add(new Coordinate(target.row() + delta.row(), target.col() + delta.col()));
            }
        }
    }

    @Override
    public Maze generate(int height, int width) {
        if (height <= MIN_SIZE || width <= MIN_SIZE) {
            throw new IllegalArgumentException();
        }

        Cell[][] maze = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = new Cell(i, j, Cell.Type.WALL);
            }
        }

        List<Coordinate> borders = new ArrayList<>();

        ArrayList<Coordinate> deltaList = new ArrayList<>();
        deltaList.add(new Coordinate(-1, 0));
        deltaList.add(new Coordinate(1, 0));
        deltaList.add(new Coordinate(0, -1));
        deltaList.add(new Coordinate(0, 1));

        Random random = new Random();
        Coordinate start = new Coordinate(random.nextInt(height - 2) + 1, random.nextInt(width - 2) + 1);
        doMove(maze, borders, deltaList, start);

        while (!borders.isEmpty()) {
            Collections.shuffle(borders);
            Coordinate currentCoordinate = borders.removeLast();
            int row = currentCoordinate.row();
            int col = currentCoordinate.col();

            if (validPosition(row, col, height, width)) {
                doMove(maze, borders, deltaList, currentCoordinate);
            }
        }

        return new Maze(maze);
    }
}
