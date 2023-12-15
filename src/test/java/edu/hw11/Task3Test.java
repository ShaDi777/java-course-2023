package edu.hw11;

import java.lang.reflect.Modifier;
import java.util.stream.Stream;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    private static final String CLASS_NAME = "FibCalculator";
    private static final String METHOD_NAME = "fib";
    private static final String METHOD_SIGNATURE = "(I)J";

    private static Stream<Arguments> paramsFibonacci() {
        return Stream.of(
            Arguments.of(0, 0),
            Arguments.of(1, 1),
            Arguments.of(2, 1),
            Arguments.of(3, 2),
            Arguments.of(4, 3),
            Arguments.of(5, 5),
            Arguments.of(6, 8),
            Arguments.of(7, 13),
            Arguments.of(8, 21),
            Arguments.of(9, 34),
            Arguments.of(10, 55)
        );
    }

    @ParameterizedTest
    @MethodSource("paramsFibonacci")
    void createClassAndMethodWithOpcodes(int n, long expectedResult) throws Exception {
        var fibClass = new ByteBuddy()
            .subclass(Object.class)
            .name(CLASS_NAME)
            .defineMethod(METHOD_NAME, long.class, Modifier.PUBLIC | Modifier.STATIC)
            .withParameters(int.class)
            .intercept(new Implementation() {
                @Override
                public ByteCodeAppender appender(Target target) {
                    return (mv, context, methodDescription) -> {
                        Label l1 = new Label();
                        mv.visitCode();

                        // if (n <= 1)
                        mv.visitVarInsn(Opcodes.ILOAD, 0);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitJumpInsn(Opcodes.IF_ICMPGT, l1);

                        //  return n
                        mv.visitVarInsn(Opcodes.ILOAD, 0);
                        mv.visitInsn(Opcodes.I2L);
                        mv.visitInsn(Opcodes.LRETURN);

                        // fib(n - 1)
                        mv.visitLabel(l1);
                        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        mv.visitVarInsn(Opcodes.ILOAD, 0);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(Opcodes.INVOKESTATIC, CLASS_NAME, METHOD_NAME, METHOD_SIGNATURE);

                        // fib(n - 2)
                        mv.visitVarInsn(Opcodes.ILOAD, 0);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(Opcodes.INVOKESTATIC, CLASS_NAME, METHOD_NAME, METHOD_SIGNATURE);

                        // fib(n - 1) + fib(n - 2);
                        mv.visitInsn(Opcodes.LADD);
                        mv.visitInsn(Opcodes.LRETURN);
                        mv.visitEnd();

                        return new ByteCodeAppender.Size(5, 2);
                    };
                }

                @Override
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }
            })
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        long result = (long) fibClass.getMethod("fib", int.class)
            .invoke(null, n);

        assertThat(result).isEqualTo(expectedResult);
    }
}
