package edu.project2.Services.Solvers;

import edu.project2.Entities.Maze;
import edu.project2.Exceptions.IllegalPathEndpointsException;
import edu.project2.Models.Coordinate;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class DfsSolver extends BaseSolver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (isInvalidEndpoints(maze, start, end)) {
            throw new IllegalPathEndpointsException();
        }

        Set<Coordinate> visited = new HashSet<>();
        Deque<Coordinate> queue = new ArrayDeque<>();

        queue.offer(start);
        while (!queue.isEmpty()) {
            Coordinate currentCoordinate = queue.getLast();
            if (currentCoordinate.equals(end)) {
                break;
            }
            int row = currentCoordinate.row();
            int col = currentCoordinate.col();

            boolean moved = false;
            for (var delta : Maze.DELTA_LIST) {
                Coordinate target = new Coordinate(row + delta.row(), col + delta.col());
                if (isPassageCoordinate(maze, target)
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
}
