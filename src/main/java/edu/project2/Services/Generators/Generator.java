package edu.project2.Services.Generators;

import edu.project2.Entities.Maze;

public interface Generator {
    Maze generate(int height, int width);
}
