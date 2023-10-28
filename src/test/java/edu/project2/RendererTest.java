package edu.project2;

import edu.project2.Entities.Maze;
import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import edu.project2.Services.Renderers.AsciiRenderer;
import edu.project2.Services.Renderers.Renderer;
import edu.project2.Services.Renderers.UnicodeRenderer;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RendererTest {
    private final Cell w = new Cell(0, 0, Cell.Type.WALL);
    private final Cell p = new Cell(0, 0, Cell.Type.PASSAGE);

    @Test
    void asciiRendererTest() {
        // Arrange
        Renderer renderer = new AsciiRenderer();
        Maze maze = new Maze(
            new Cell[][] {
                {w, w, w, w, w},
                {w, p, p, p, w},
                {w, p, p, p, w},
                {w, p, p, p, w},
                {w, w, w, w, w},
            },
            new Coordinate(1,1),
            new Coordinate(1, 1)
        );
        String expectedRender =
            """
            #####
            #   #
            #   #
            #   #
            #####
            """;

        // Act
        String mazeRender = renderer.render(maze);

        // Assert
        assertThat(mazeRender).isEqualTo(expectedRender);
    }

    @Test
    void asciiRendererWithPathTest() {
        // Arrange
        Renderer renderer = new AsciiRenderer();
        Maze maze = new Maze(
            new Cell[][] {
                {w, w, w, w, w},
                {w, p, p, p, w},
                {w, p, p, p, w},
                {w, p, p, p, w},
                {w, w, w, w, w},
            },
            new Coordinate(1,1),
            new Coordinate(1, 1)
        );
        List<Coordinate> path = List.of(
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(2, 2),
            new Coordinate(3, 2),
            new Coordinate(3, 3)
        );
        String expectedRender =
            """
            #####
            #** #
            # * #
            # **#
            #####
            """;

        // Act
        String mazeRender = renderer.render(maze, path);

        // Assert
        assertThat(mazeRender).isEqualTo(expectedRender);
    }

    @Test
    void unicodeRendererTest() {
        // Arrange
        Renderer renderer = new UnicodeRenderer();
        Maze maze = new Maze(
            new Cell[][] {
                {w, w, w, w, w, w},
                {w, p, p, p, p, w},
                {w, p, p, p, p, w},
                {w, p, p, p, p, w},
                {w, w, w, w, w, w},
            },
            new Coordinate(1,1),
            new Coordinate(1, 1)
        );
        String expectedRender =
            """
            ██████
            █    █
            █    █
            █    █
            ██████
            """;

        // Act
        String mazeRender = renderer.render(maze);

        // Assert
        assertThat(mazeRender).isEqualTo(expectedRender);
    }

    @Test
    void unicodeRendererWithPathTest() {
        // Arrange
        Renderer renderer = new UnicodeRenderer();
        Maze maze = new Maze(
            new Cell[][] {
                {w, w, w, w, w, w},
                {w, p, p, p, p, w},
                {w, p, p, p, p, w},
                {w, p, p, p, p, w},
                {w, w, w, w, w, w},
            },
            new Coordinate(1,1),
            new Coordinate(1, 1)
        );
        List<Coordinate> path = List.of(
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(2, 2),
            new Coordinate(3, 2),
            new Coordinate(3, 3),
            new Coordinate(3, 4),
            new Coordinate(2, 4),
            new Coordinate(1, 4)
        );
        String expectedRender =
            """
            ██████
            █✓✓ ✓█
            █ ✓ ✓█
            █ ✓✓✓█
            ██████
            """;

        // Act
        String mazeRender = renderer.render(maze, path);

        // Assert
        assertThat(mazeRender).isEqualTo(expectedRender);
    }
}
