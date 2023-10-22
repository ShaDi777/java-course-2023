package edu.project2;

import edu.project2.Entities.Maze;
import edu.project2.Models.Cell;
import edu.project2.Services.Generators.DfsGenerator;
import edu.project2.Services.Generators.Generator;
import edu.project2.Services.Generators.PrimGenerator;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeneratorTests {
    private static Stream<Arguments> paramGenerator() {
        return Stream.of(
            Arguments.of(new DfsGenerator()),
            Arguments.of(new PrimGenerator())
        );
    }

    @ParameterizedTest
    @MethodSource("paramGenerator")
    void Generator_ShouldThrow_WhenSizeIncorrect(Generator generator) {
        // Arrange
        int heightNormal = 10;
        int widthNormal = 10;

        int heightNegative = -10;
        int widthNegative = -10;

        int height0 = 0;
        int width0 = 0;

        int heightSmall = 1;
        int widthSmall = 1;

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> generator.generate(heightNormal, widthNegative));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(heightNormal, width0));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(heightNormal, widthSmall));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(heightSmall, widthNormal));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(height0, widthNormal));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(heightNegative, widthNormal));
    }

    @ParameterizedTest
    @MethodSource("paramGenerator")
    void Generator_ShouldReturnMazeWithBorders_WhenCorrectArguments(Generator generator) {
        // Arrange
        int height = 15;
        int width = 10;

        // Act
        Maze maze = generator.generate(height, width);

        // Assert
        assertThat(maze).isNotNull();
        assertThat(maze.getHeight()).isEqualTo(height);
        assertThat(maze.getWidth()).isEqualTo(width);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                assertThat(maze.getGrid()[row][col].row()).isEqualTo(row);
                assertThat(maze.getGrid()[row][col].col()).isEqualTo(col);
                if (row == 0 || row == height - 1
                    || col == 0 || col == width - 1) {
                    assertThat(maze.getGrid()[row][col].type()).isEqualTo(Cell.Type.WALL);
                }
            }
        }
    }

}
