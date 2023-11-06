package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    private static Stream<Arguments> paramsValidLicensePlate() {
        return Stream.of(
            Arguments.of("A123BC54"),
            Arguments.of("O456PK154"),
            Arguments.of("E999XM178"),
            Arguments.of("Y555CT98"),
            Arguments.of("O777OO177"),
            Arguments.of("A123BE777")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsValidLicensePlate")
    void testValidPassword(String validLicensePlate) {
        assertThat(Task5.isValidRussianLicensePlate(validLicensePlate)).isTrue();
    }

    private static Stream<Arguments> paramsInvalidLicensePlate() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("123АВЕ777"),
            Arguments.of("А123ВЕ7777"),
            Arguments.of("АB123Е777"),
            Arguments.of("Й999ЦЪ999"),
            Arguments.of("А345БВ77"),
            Arguments.of("О22ОО11"),
            Arguments.of("А123ВС1234")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidLicensePlate")
    void testInvalidPassword(String invalidLicensePlate) {
        assertThat(Task5.isValidRussianLicensePlate(invalidLicensePlate)).isFalse();
    }
}
