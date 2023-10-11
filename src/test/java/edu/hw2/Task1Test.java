package edu.hw2;

import edu.hw2.Task1.Expr;
import edu.hw2.Task1.Expr.Addition;
import edu.hw2.Task1.Expr.Constant;
import edu.hw2.Task1.Expr.Exponent;
import edu.hw2.Task1.Expr.Multiplication;
import edu.hw2.Task1.Expr.Negate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    private final static Logger LOGGER = LogManager.getLogger();

    @Test
    void complexTest() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        LOGGER.trace("{} = {}", res, res.evaluate());
        assertThat(res.evaluate()).isEqualTo(37.0);
    }

    static Arguments[] additionValues() {
        return new Arguments[]{
            Arguments.of(new Constant(-10), new Constant(10), 0.),
            Arguments.of(new Constant(0), new Constant(5), 5.),
            Arguments.of(new Constant(-5), new Constant(-5), -10.),
            Arguments.of(new Constant(1), new Constant(2), 3.),
        };
    }

    @ParameterizedTest
    @MethodSource("additionValues")
    void additionTest(Expr left, Expr right, double expectedResult) {
        var expr = new Addition(left, right);

        assertThat(expr.evaluate()).isEqualTo(expectedResult);
    }


    static Arguments[] negationValues() {
        return new Arguments[]{
            Arguments.of(new Constant(10), -10.0),
            Arguments.of(new Constant(0), 0.0),
            Arguments.of(new Constant(-1), 1.0),
        };
    }

    @ParameterizedTest
    @MethodSource("negationValues")
    void negationTest(Expr expr, double expectedResult) {
        var negatedExpr = new Negate(expr);

        assertThat(negatedExpr.evaluate()).isEqualTo(expectedResult);
    }


    static Arguments[] multiplicationValues() {
        return new Arguments[]{
            Arguments.of(new Constant(10), new Constant(10), 100.),
            Arguments.of(new Constant(-10), new Constant(-10), 100.),
            Arguments.of(new Constant(-10), new Constant(10), -100.),
            Arguments.of(new Constant(10), new Constant(-10), -100.),
            Arguments.of(new Constant(0), new Constant(55), 0.),
            Arguments.of(new Constant(10), new Constant(0), 0.),
            Arguments.of(new Constant(0), new Constant(0), 0.),
            Arguments.of(new Constant(2), new Constant(3), 6.),
        };
    }

    @ParameterizedTest
    @MethodSource("multiplicationValues")
    void multiplicationTest(Expr left, Expr right, double expectedResult) {
        var expr = new Multiplication(left, right);

        assertThat(expr.evaluate()).isEqualTo(expectedResult);
    }


    static Arguments[] exponentValues() {
        return new Arguments[]{
            Arguments.of(new Constant(10), 0, 1.),
            Arguments.of(new Constant(10), 1, 10.),
            Arguments.of(new Constant(10), 2, 100.),
            Arguments.of(new Constant(10), -1, 0.1),
            Arguments.of(new Constant(-10), 0, 1.),
            Arguments.of(new Constant(-10), 1, -10.),
            Arguments.of(new Constant(-10), 2, 100.),
            Arguments.of(new Constant(-10), -1, -0.1),
        };
    }

    @ParameterizedTest
    @MethodSource("exponentValues")
    void exponentTest(Expr base, long power, double expectedResult) {
        var expr = new Exponent(base, power);

        assertThat(expr.evaluate()).isEqualTo(expectedResult);
    }
}
