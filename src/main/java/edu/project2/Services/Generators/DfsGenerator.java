package edu.project2.Services.Generators;

import edu.project2.Entities.Maze;
import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class DfsGenerator implements Generator {
    private static final int MIN_SIZE = 3;

    private boolean validPosition(int row, int col, int height, int width) {
        return col >= 1 && row >= 1 && row < height - 1 && col < width - 1;
    }

    private boolean doMove(
        Cell[][] maze,
        Queue<Coordinate> queue,
        Set<Coordinate> visited,
        Coordinate currentCoordinate,
        int rowDelta, int colDelta
    ) {
        Coordinate target = new Coordinate(currentCoordinate.row() + rowDelta, currentCoordinate.col() + colDelta);

        int countVisitedNeighbours = 0;
        countVisitedNeighbours += visited.contains(new Coordinate(target.row() - 1, target.col())) ? 1 : 0;
        countVisitedNeighbours += visited.contains(new Coordinate(target.row() + 1, target.col())) ? 1 : 0;
        countVisitedNeighbours += visited.contains(new Coordinate(target.row(), target.col() - 1)) ? 1 : 0;
        countVisitedNeighbours += visited.contains(new Coordinate(target.row(), target.col() + 1)) ? 1 : 0;

        if (!visited.contains(target) && countVisitedNeighbours <= 1) {
            maze[target.row()][target.col()] = new Cell(target.row(), target.col(), Cell.Type.PASSAGE);
            visited.add(target);
            queue.offer(target);
            return true;
        }
        return false;
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

        Set<Coordinate> visited = new HashSet<>();
        Deque<Coordinate> queue = new ArrayDeque<>();

        ArrayList<Coordinate> deltaList = new ArrayList<>();
        deltaList.add(new Coordinate(-1, 0));
        deltaList.add(new Coordinate(1, 0));
        deltaList.add(new Coordinate(0, -1));
        deltaList.add(new Coordinate(0, 1));

        Random random = new Random();
        Coordinate start = new Coordinate(random.nextInt(height - 2) + 1, random.nextInt(width - 2) + 1);
        doMove(maze, queue, visited, start, 0, 0);

        while (!queue.isEmpty()) {
            Coordinate currentCoordinate = queue.getLast();
            int row = currentCoordinate.row();
            int col = currentCoordinate.col();

            boolean moved = false;
            Collections.shuffle(deltaList);
            for (var delta : deltaList) {
                if (validPosition(row + delta.row(), col + delta.col(), height, width)
                    && doMove(maze, queue, visited, currentCoordinate, delta.row(), delta.col())) {
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                queue.pollLast();
            }
        }

        return new Maze(maze);
    }
}
