package edu.project2.Services.Generators;

import edu.project2.Entities.Maze;
import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimGenerator extends BaseGenerator {
    @Override
    public Maze generate(int height, int width) {
        validateSize(height, width);
        Cell[][] maze = generateEmpty(height, width);

        List<Coordinate> borders = new ArrayList<>();

        Coordinate start = getRandomStart(height, width);
        //maze[start.row()][start.col()] = new Cell(start.row(), start.col(), Cell.Type.PASSAGE);
        doMove(maze, borders, start);

        while (!borders.isEmpty()) {
            Collections.shuffle(borders);
            Coordinate currentCoordinate = borders.removeLast();

            if (isInsideMaze(currentCoordinate, height, width)) {
                doMove(maze, borders, currentCoordinate);
            }
        }
        Coordinate end = getRandomEnd(maze);
        maze[end.row()][end.col()] = new Cell(end.row(), end.col(), Cell.Type.PASSAGE);
        return new Maze(maze, start, end);
    }

    private int countBorders(Cell[][] maze, Coordinate current) {
        int countPossibleBorders = 0;
        for (var delta : Maze.DELTA_LIST) {
            var target = new Coordinate(current.row() + delta.row(), current.col() + delta.col());
            if (!isInsideMaze(target, maze.length, maze[0].length)
                || maze[target.row()][target.col()].type() == Cell.Type.WALL) {
                countPossibleBorders++;
            }
        }

        return countPossibleBorders;
    }

    private void doMove(
        Cell[][] maze,
        List<Coordinate> borders,
        Coordinate target
    ) {
        if (maze[target.row()][target.col()].type() == Cell.Type.PASSAGE
            || countBorders(maze, target) < MIN_SIZE) {
            return;
        }

        maze[target.row()][target.col()] = new Cell(target.row(), target.col(), Cell.Type.PASSAGE);
        for (var delta : Maze.DELTA_LIST) {
            var newTarget = new Coordinate(target.row() + delta.row(), target.col() + delta.col());
            if (isInsideMaze(newTarget, maze.length, maze[0].length)
                && maze[newTarget.row()][newTarget.col()].type() == Cell.Type.WALL) {
                borders.add(newTarget);
            }
        }
    }
}
