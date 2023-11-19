package edu.hw7;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class FactorialTest {
    public static Stream<Arguments> paramsFactorial() {
        return Stream.of(
            Arguments.of(0, 1),
            Arguments.of(1, 1),
            Arguments.of(2, 2),
            Arguments.of(3, 6),
            Arguments.of(4, 24),
            Arguments.of(5, 120),
            Arguments.of(6, 720),
            Arguments.of(7, 5_040),
            Arguments.of(8, 40_320),
            Arguments.of(9, 362_880),
            Arguments.of(10, 3_628_800)
        );
    }
    @ParameterizedTest
    @MethodSource("paramsFactorial")
    void parallelFactorial(long n, long expectedResult) {
        long result = ParallelFactorial.getFactorial(n);
        assertThat(result).isEqualTo(expectedResult);
    }
}
