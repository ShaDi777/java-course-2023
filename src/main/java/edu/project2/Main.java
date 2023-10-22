package edu.project2;

import edu.project2.Entities.Maze;
import edu.project2.Models.Coordinate;
import edu.project2.Services.Generators.Generator;
import edu.project2.Services.Generators.PrimGenerator;
import edu.project2.Services.Renderers.PrettyRenderer;
import edu.project2.Services.Renderers.Renderer;
import edu.project2.Services.Solvers.BfsSolver;
import edu.project2.Services.Solvers.Solver;
import java.util.List;

public final class Main {
    private static final int HEIGHT = 15;
    private static final int WIDTH = 15;

    private Main() {
    }

    // Console example
    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public static void main(String[] args) {
        Generator generator = new PrimGenerator();
        Maze maze = generator.generate(HEIGHT, WIDTH);

        Solver solver = new BfsSolver();
        List<Coordinate> path = solver.solve(maze, new Coordinate(1, 1), new Coordinate(HEIGHT - 2, WIDTH - 2));

        Renderer renderer = new PrettyRenderer(true);
        System.out.println(renderer.render(maze));
        System.out.println(renderer.render(maze, path));
    }
}
