package edu.project2.Services.Renderers;

import edu.project2.Entities.Maze;
import edu.project2.Models.Coordinate;
import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);
}
