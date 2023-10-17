package edu.hw3;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    private static Stream<Arguments> paramsNumbersAndRomans() {
        return Stream.of(
            Arguments.of(-1, null),
            Arguments.of(0, null),
            Arguments.of(4000, null),
            Arguments.of(1, "I"),
            Arguments.of(2, "II"),
            Arguments.of(3, "III"),
            Arguments.of(4, "IV"),
            Arguments.of(5, "V"),
            Arguments.of(6, "VI"),
            Arguments.of(7, "VII"),
            Arguments.of(8, "VIII"),
            Arguments.of(9, "IX"),
            Arguments.of(10, "X"),
            Arguments.of(99, "XCIX"),
            Arguments.of(150, "CL"),
            Arguments.of(455, "CDLV"),
            Arguments.of(501, "DI"),
            Arguments.of(987, "CMLXXXVII"),
            Arguments.of(3999, "MMMCMXCIX")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsNumbersAndRomans")
    void toRomanTest(int num, String expectedResult) {
        String actualResult = Task4.convertToRoman(num);

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
