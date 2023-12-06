package edu.project2.Services.Solvers;

import edu.project2.Entities.Maze;
import edu.project2.Exceptions.IllegalPathEndpointsException;
import edu.project2.Models.Coordinate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DfsParallelSolver extends BaseSolver {
    private static final int TIME_LOCK_SECONDS = 60;
    private final ReentrantLock lock = new ReentrantLock();
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    private final List<Coordinate> path = new ArrayList<>();
    private boolean[][] visited;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (isInvalidEndpoints(maze, start, end)) {
            throw new IllegalPathEndpointsException();
        }

        visited = new boolean[maze.getHeight()][maze.getWidth()];

        boolean result = forkJoinPool.invoke(new DfsParallelTask(maze, start, end));
        if (!result) {
            return List.of();
        }
        path.add(end);
        return path;
    }

    private class DfsParallelTask extends RecursiveTask<Boolean> {

        private final Maze maze;
        private final Coordinate current;
        private final Coordinate end;

        DfsParallelTask(Maze maze, Coordinate current, Coordinate end) {
            this.maze = maze;
            this.current = current;
            this.end = end;
        }

        @Override
        protected Boolean compute() {
            List<Coordinate> coordinatesForFork = new ArrayList<>();
            coordinatesForFork.add(current);
            try {
                lock.tryLock(TIME_LOCK_SECONDS, TimeUnit.SECONDS);
                visited[current.row()][current.col()] = true;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            if (current.equals(end)) {
                return true;
            }
            List<DfsParallelTask> forks = new ArrayList<>();
            for (var delta : Maze.DELTA_LIST) {
                Coordinate target = new Coordinate(current.row() + delta.row(), current.col() + delta.col());
                if (!isPassageCoordinate(maze, target)) {
                    continue;
                }
                if (!visited[target.row()][target.col()]) {
                    DfsParallelTask task = new DfsParallelTask(maze, target, end);
                    task.fork();
                    forks.add(task);
                }
            }
            for (DfsParallelTask task : forks) {
                if (task.join()) {
                    coordinatesForFork.forEach(path::addFirst);
                    return true;
                }
            }
            return false;
        }
    }
}
