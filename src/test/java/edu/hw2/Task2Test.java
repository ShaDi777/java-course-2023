package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleAreaTest(Rectangle rect) {
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);

        assertThat(rect.area()).isEqualTo(200.0);
    }

    @Test
    void squareResizeTest() {
        Square square = new Square();

        square = square.setSideSize(10);

        assertThat(square.area()).isEqualTo(100.0);
    }

    @Test
    void rectangleResizeTest() {
        Rectangle rectangle = new Rectangle();

        rectangle = rectangle.setHeight(100);
        rectangle = rectangle.setWidth(1);

        assertThat(rectangle.area()).isEqualTo(100.0);
    }

    @Test
    void squareConstructorTest() {
        Rectangle rectangle = new Square(10);

        assertThat(rectangle.area()).isEqualTo(100.0);
    }

    @Test
    void rectangleConstructorTest() {
        Rectangle rectangle = new Square(10);

        assertThat(rectangle.area()).isEqualTo(100.0);
    }

    @Test
    void negativeSizeTest() {
        assertThrows(IllegalArgumentException.class, () -> new Square(-10));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(5, -10));
    }
}
