package edu.hw11;

import java.util.stream.Stream;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    private static Stream<Arguments> paramsRedefinition() {
        return Stream.of(
            Arguments.of(0, 0, 0, 0),
            Arguments.of(1, 1, 2, 1),
            Arguments.of(-1, -1, -2, 1),
            Arguments.of(5, 5, 10, 25),
            Arguments.of(0, 9, 9, 0),
            Arguments.of(-3, 7, 4, -21)
        );
    }

    @ParameterizedTest
    @MethodSource("paramsRedefinition")
    void redefineMethodBehaviour(int a, int b, int sum, int mult) throws Exception {
        ByteBuddyAgent.install();
        try {
            new ByteBuddy()
                .redefine(ArithmeticUtils.class)
                .method(ElementMatchers.named("sum").and(ElementMatchers.returns(int.class)))
                .intercept(MethodDelegation.to(ArithmeticInterceptor.class))
                .make()
                .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

            assertThat(ArithmeticUtils.sum(3, 5)).isEqualTo(15);
        } finally {
            ClassReloadingStrategy.fromInstalledAgent().reset(ArithmeticUtils.class);
        }

        assertThat(ArithmeticUtils.sum(3, 5)).isEqualTo(8);
    }

    public class ArithmeticUtils {
        public static int sum(int a, int b) {
            return a + b;
        }
    }

    public static class ArithmeticInterceptor {
        public static int sum(int a, int b) {
            return a * b;
        }
    }
}
