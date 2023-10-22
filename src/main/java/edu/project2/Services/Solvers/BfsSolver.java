package edu.project2.Services.Solvers;

import edu.project2.Entities.Maze;
import edu.project2.Exceptions.IllegalPathEndpoints;
import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BfsSolver implements Solver {
    private boolean validPosition(int row, int col, int height, int width) {
        return col >= 0 && row >= 0 && row < height && col < width;
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

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (!validPosition(start.row(), start.col(), maze.getHeight(), maze.getWidth())
            || !validPosition(end.row(), end.col(), maze.getHeight(), maze.getWidth())
            || maze.getGrid()[start.row()][start.col()].type() == Cell.Type.WALL
            || maze.getGrid()[end.row()][end.col()].type() == Cell.Type.WALL) {
            throw new IllegalPathEndpoints();
        }

        Map<Coordinate, List<Coordinate>> paths = new HashMap<>();
        Queue<Coordinate> queue = new ArrayDeque<>();

        ArrayList<Coordinate> deltaList = new ArrayList<>();
        deltaList.add(new Coordinate(-1, 0));
        deltaList.add(new Coordinate(1, 0));
        deltaList.add(new Coordinate(0, -1));
        deltaList.add(new Coordinate(0, 1));

        paths.put(start, new ArrayList<>(List.of(start)));
        queue.offer(start);
        while (!queue.isEmpty()) {
            Coordinate currentCoordinate = queue.poll();
            if (currentCoordinate.equals(end)) {
                break;
            }
            int row = currentCoordinate.row();
            int col = currentCoordinate.col();

            for (var delta : deltaList) {
                if (validPosition(row + delta.row(), col + delta.col(), maze.getHeight(), maze.getWidth())
                    && maze.getGrid()[row + delta.row()][col + delta.col()].type() == Cell.Type.PASSAGE) {
                    doMove(queue, paths, currentCoordinate, delta.row(), delta.col());
                }
            }
        }
        return paths.getOrDefault(end, List.of());
    }
}
