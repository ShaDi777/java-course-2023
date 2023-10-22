package edu.project2.Services.Solvers;

import edu.project2.Entities.Maze;
import edu.project2.Exceptions.IllegalPathEndpoints;
import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class DfsSolver implements Solver {
    private boolean validPosition(int row, int col, int height, int width) {
        return col >= 0 && row >= 0 && row < height && col < width;
    }

    private boolean doMove(
        Queue<Coordinate> queue,
        Set<Coordinate> visited,
        Coordinate currentCoordinate,
        int rowDelta, int colDelta
    ) {
        Coordinate target = new Coordinate(currentCoordinate.row() + rowDelta, currentCoordinate.col() + colDelta);
        if (!visited.contains(target) && !queue.contains(target)) {
            visited.add(target);
            queue.offer(target);
            return true;
        }
        return false;
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (!validPosition(start.row(), start.col(), maze.getHeight(), maze.getWidth())
            || !validPosition(end.row(), end.col(), maze.getHeight(), maze.getWidth())
            || maze.getGrid()[start.row()][start.col()].type() == Cell.Type.WALL
            || maze.getGrid()[end.row()][end.col()].type() == Cell.Type.WALL) {
            throw new IllegalPathEndpoints();
        }

        Set<Coordinate> visited = new HashSet<>();
        Deque<Coordinate> queue = new ArrayDeque<>();

        ArrayList<Coordinate> deltaList = new ArrayList<>();
        deltaList.add(new Coordinate(-1, 0));
        deltaList.add(new Coordinate(1, 0));
        deltaList.add(new Coordinate(0, -1));
        deltaList.add(new Coordinate(0, 1));

        queue.offer(start);
        while (!queue.isEmpty()) {
            Coordinate currentCoordinate = queue.getLast();
            if (currentCoordinate.equals(end)) {
                break;
            }
            int row = currentCoordinate.row();
            int col = currentCoordinate.col();

            boolean moved = false;
            for (var delta : deltaList) {
                if (validPosition(row + delta.row(), col + delta.col(), maze.getHeight(), maze.getWidth())
                    && maze.getGrid()[row + delta.row()][col + delta.col()].type() == Cell.Type.PASSAGE
                    && doMove(queue, visited, currentCoordinate, delta.row(), delta.col())) {
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                visited.add(currentCoordinate);
                queue.pollLast();
            }
        }
        return queue.stream().toList();
    }
}
