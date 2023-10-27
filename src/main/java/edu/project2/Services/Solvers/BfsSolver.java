package edu.project2.Services.Solvers;

import edu.project2.Entities.Maze;
import edu.project2.Exceptions.IllegalPathEndpointsException;
import edu.project2.Models.Coordinate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BfsSolver extends BaseSolver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (isInvalidEndpoints(maze, start, end)) {
            throw new IllegalPathEndpointsException();
        }

        Map<Coordinate, List<Coordinate>> paths = new HashMap<>();
        Queue<Coordinate> queue = new ArrayDeque<>();

        paths.put(start, new ArrayList<>(List.of(start)));
        queue.offer(start);
        while (!queue.isEmpty()) {
            Coordinate currentCoordinate = queue.poll();
            if (currentCoordinate.equals(end)) {
                break;
            }
            int row = currentCoordinate.row();
            int col = currentCoordinate.col();

            for (var delta : Maze.DELTA_LIST) {
                Coordinate target = new Coordinate(row + delta.row(), col + delta.col());
                if (isPassageCoordinate(maze, target)) {
                    doMove(queue, paths, currentCoordinate, delta.row(), delta.col());
                }
            }
        }
        return paths.getOrDefault(end, List.of());
    }

    private void doMove(
        Queue<Coordinate> queue,
        Map<Coordinate, List<Coordinate>> paths,
        Coordinate currentCoordinate,
        int rowDelta, int colDelta
    ) {
        Coordinate target = new Coordinate(currentCoordinate.row() + rowDelta, currentCoordinate.col() + colDelta);
        if (!paths.containsKey(target)) {
            List<Coordinate> currentPath = new ArrayList<>(paths.get(currentCoordinate));
            currentPath.add(target);
            paths.put(target, currentPath);
            queue.offer(target);
        }
    }
}
