package edu.hw3;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    private static Stream<Arguments> paramsTextAndAtbashEncodedText() {
        return Stream.of(
            Arguments.of(null, null),
            Arguments.of("", ""),
            Arguments.of(
                "1234567890",
                "1234567890"
            ),
            Arguments.of(
                "!@#$%^&*()_+-=/*",
                "!@#$%^&*()_+-=/*"
            ),
            Arguments.of(
                "Не латинские символы",
                "Не латинские символы"
            ),
            Arguments.of(
                "ABCDEabcde",
                "ZYXWVzyxwv"
            ),
            Arguments.of(
                "abcdefghijklmnopqrstuvwxyz",
                "zyxwvutsrqponmlkjihgfedcba"
            ),
            Arguments.of(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                "ZYXWVUTSRQPONMLKJIHGFEDCBA"
            ),
            Arguments.of(
                "Hello world!",
                "Svool dliow!"
            ),
            Arguments.of(
                "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsTextAndAtbashEncodedText")
    void encodeAtbashTest(String text, String expectedResult) {
        // Arrange in parameters
        // Act
        String actualResult = Task1.encodeAtbash(text);

        // Assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
