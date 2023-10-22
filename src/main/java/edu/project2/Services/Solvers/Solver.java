package edu.project2.Services.Solvers;

import edu.project2.Entities.Maze;
import edu.project2.Models.Coordinate;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
