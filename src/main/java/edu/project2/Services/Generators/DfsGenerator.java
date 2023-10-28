package edu.project2.Services.Generators;

import edu.project2.Entities.Maze;
import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class DfsGenerator extends BaseGenerator {
    @Override
    public Maze generate(int height, int width) {
        validateSize(height, width);
        Cell[][] maze = generateEmpty(height, width);

        Set<Coordinate> visited = new HashSet<>();
        Deque<Coordinate> queue = new ArrayDeque<>();

        List<Coordinate> deltaList = new ArrayList<>(Maze.DELTA_LIST);

        Coordinate start = getRandomStart(height, width);
        maze[start.row()][start.col()] = new Cell(start.row(), start.col(), Cell.Type.PASSAGE);
        doMove(maze, queue, visited, start);

        while (!queue.isEmpty()) {
            Coordinate currentCoordinate = queue.getLast();

            boolean moved = false;
            Collections.shuffle(deltaList);
            for (var delta : deltaList) {
                Coordinate target = new Coordinate(
                    currentCoordinate.row() + delta.row(),
                    currentCoordinate.col() + delta.col()
                );
                if (isInsideMaze(target, height, width)
                    && doMove(maze, queue, visited, target)) {
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                queue.pollLast();
            }
        }
        Coordinate end = getRandomEnd(maze);
        maze[end.row()][end.col()] = new Cell(end.row(), end.col(), Cell.Type.PASSAGE);
        return new Maze(maze, start, end);
    }

    private boolean doMove(
        Cell[][] maze,
        Queue<Coordinate> queue,
        Set<Coordinate> visited,
        Coordinate target
    ) {
        int countVisitedNeighbours = 0;
        for (var delta : Maze.DELTA_LIST) {
            Coordinate neighbour = new Coordinate(
                target.row() + delta.row(),
                target.col() + delta.col()
            );
            if (visited.contains(neighbour)) {
                countVisitedNeighbours++;
            }
        }

        if (!visited.contains(target) && countVisitedNeighbours <= 1) {
            maze[target.row()][target.col()] = new Cell(target.row(), target.col(), Cell.Type.PASSAGE);
            visited.add(target);
            queue.offer(target);
            return true;
        }
        return false;
    }
}
