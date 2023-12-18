package edu.hw10;

import edu.hw10.models.task1.ClassWithMultipleConstructors;
import edu.hw10.models.task1.ClassWithStaticCreate;
import edu.hw10.models.task1.RecordWithAllTypes;
import edu.hw10.task1.RandomObjectGenerator;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomObjectGeneratorTests {
    private final RandomObjectGenerator rog = new RandomObjectGenerator();

    @Test
    void createObjectWithConstructor()
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var clazz = rog.nextObject(ClassWithMultipleConstructors.class);

        assertThat(clazz.getStringValue()).isNotNull();
        assertThat(clazz.getIntValue()).isGreaterThanOrEqualTo(10);
        assertThat(clazz.getDoubleValue()).isLessThanOrEqualTo(3.14);
        assertThat(clazz.getFloatValue()).isBetween(0.5f, 2.5f);
    }

    @Test
    void createObjectWithStaticMethod()
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var clazz = rog.nextObject(ClassWithStaticCreate.class, "create");

        assertThat(clazz.getStringValue()).isNotNull();
        assertThat(clazz.getIntValue()).isGreaterThanOrEqualTo(10);
        assertThat(clazz.getDoubleValue()).isLessThanOrEqualTo(3.14);
        assertThat(clazz.getFloatValue()).isBetween(0.5f, 2.5f);
    }

    @Test
    void createRecord() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var record = rog.nextObject(RecordWithAllTypes.class);

        assertThat(record.stringValue()).isNotNull();
        assertThat(record.intValue()).isGreaterThanOrEqualTo(10);
        assertThat(record.doubleValue()).isLessThanOrEqualTo(3.14);
        assertThat(record.floatValue()).isBetween(0.5f, 2.5f);
    }

    @Test
    public void exceptionFromNonExistentMethod() {
        assertThrows(
            NoSuchElementException.class,
            () -> rog.nextObject(RecordWithAllTypes.class, "someUnknownMethod")
        );
    }
}
