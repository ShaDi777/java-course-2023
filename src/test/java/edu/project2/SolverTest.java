package edu.project2;

import edu.project2.Entities.Maze;
import edu.project2.Exceptions.IllegalPathEndpointsException;
import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import edu.project2.Services.Solvers.BfsSolver;
import edu.project2.Services.Solvers.DfsParallelSolver;
import edu.project2.Services.Solvers.DfsSolver;
import edu.project2.Services.Solvers.Solver;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SolverTest {
    private final Cell w = new Cell(0, 0, Cell.Type.WALL);
    private final Cell p = new Cell(0, 0, Cell.Type.PASSAGE);

    private static Stream<Arguments> paramSolver() {
        return Stream.of(
            Arguments.of(new DfsSolver()),
            Arguments.of(new BfsSolver()),
            Arguments.of(new DfsParallelSolver())
        );
    }

    @ParameterizedTest
    @MethodSource("paramSolver")
    void solver_ShouldFindPath_WhenOnlyOneCell(Solver solver) {
        // Arrange
        Maze maze = new Maze(
            new Cell[][] {
                {w, w, w},
                {w, p, w},
                {w, w, w},
            },
            new Coordinate(1, 1),
            new Coordinate(1, 1)
        );

        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(1, 1);
        List<Coordinate> expectedPath = List.of(
            new Coordinate(1, 1)
        );

        // Act
        List<Coordinate> path = solver.solve(maze, start, end);

        // Assert
        assertThat(path).isEqualTo(expectedPath);
    }

    @ParameterizedTest
    @MethodSource("paramSolver")
    void solver_ShouldFindPath_WhenOnlyOneWayPossible(Solver solver) {
        // Arrange
        Maze maze = new Maze(
            new Cell[][] {
                {w, w, w, w, w, w},
                {w, p, p, p, p, w},
                {w, w, w, w, w, w},
            },
            new Coordinate(1, 1),
            new Coordinate(1, 4)
        );

        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(1, 4);
        List<Coordinate> expectedPath = List.of(
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(1, 3),
            new Coordinate(1, 4)
        );

        // Act
        List<Coordinate> path = solver.solve(maze, start, end);

        // Assert
        assertThat(path).isEqualTo(expectedPath);
    }

    @ParameterizedTest
    @MethodSource("paramSolver")
    void solver_ShouldNotFindPath_WhenNoPathPossible(Solver solver) {
        // Arrange
        Maze maze = new Maze(
            new Cell[][] {
                {w, w,  w, w, w},
                {w, p,  w, p, w},
                {w, p,  w, p, w},
                {w, w,  w, w, w},
            },
            new Coordinate(1, 1),
            new Coordinate(2, 3)
        );

        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(2, 3);
        List<Coordinate> expectedPath = List.of();

        // Act
        List<Coordinate> path = solver.solve(maze, start, end);

        // Assert
        assertThat(path).isEqualTo(expectedPath);
    }

    @ParameterizedTest
    @MethodSource("paramSolver")
    void solver_ShouldThrow_WhenWrongEndpoints(Solver solver) {
        // Arrange
        Maze maze = new Maze(
            new Cell[][] {
                {w, w, w, w, w},
                {w, p, p, p, w},
                {w, w, w, w, w},
            },
            new Coordinate(1, 1),
            new Coordinate(1, 3)
        );

        Coordinate startNormal = new Coordinate(1, 1);
        Coordinate endNormal = new Coordinate(1, 3);

        Coordinate startInWall = new Coordinate(0, 0);
        Coordinate endInWall = new Coordinate(2, 4);

        Coordinate startOutOfBounce = new Coordinate(-1, -1);
        Coordinate endOutOfBounce = new Coordinate(10000, 10000);

        // Act
        // Assert
        assertThrows(IllegalPathEndpointsException.class, () -> solver.solve(maze, startInWall, endNormal));
        assertThrows(IllegalPathEndpointsException.class, () -> solver.solve(maze, startOutOfBounce, endNormal));
        assertThrows(IllegalPathEndpointsException.class, () -> solver.solve(maze, startNormal, endInWall));
        assertThrows(IllegalPathEndpointsException.class, () -> solver.solve(maze, startNormal, endOutOfBounce));
    }

    @Test
    void bfsSolver_ShouldFindShortestPath_WhenTwoPathsPossible() {
        // Arrange
        Maze maze = new Maze(
            new Cell[][] {
                {w, w, w, w, w, w, w},
                {w, p, p, p, p, w, w},
                {w, w, p, w, p, p, w},
                {w, w, p, p, p, w, w},
                {w, w, w, w, w, w, w},
            },
            new Coordinate(1, 1),
            new Coordinate(1, 1)
        );

        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(2, 5);
        List<Coordinate> expectedPath = List.of(
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(1, 3),
            new Coordinate(1, 4),
            new Coordinate(2, 4),
            new Coordinate(2, 5)
        );

        // Act
        List<Coordinate> path = (new BfsSolver()).solve(maze, start, end);

        // Assert
        assertThat(path).isEqualTo(expectedPath);
    }
}
