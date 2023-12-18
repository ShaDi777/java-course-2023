package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    void classCreation() throws Exception {
        String fixedString = "Hello, ByteBuddy!";

        Class<?> testClass = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value(fixedString))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        assertThat(testClass.getDeclaredConstructor().newInstance().toString()).isEqualTo(fixedString);
    }
}
