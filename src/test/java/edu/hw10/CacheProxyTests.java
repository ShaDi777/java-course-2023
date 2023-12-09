package edu.hw10;

import edu.hw10.models.task2.FibonacciCalculator;
import edu.hw10.models.task2.RecursiveFibonacciCalculator;
import edu.hw10.task2.CacheProxy;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

public class CacheProxyTests {
    private @TempDir Path cacheDir;

    @ParameterizedTest
    @ValueSource(classes = {RecursiveFibonacciCalculator.class, FibonacciCalculator.class})
    void callExactlyOneTime(Class<? extends FibonacciCalculator> calculatorClass) {
        long[] expectedResults = {1, 1, 2, 3, 5, 8, 13, 21, 34};

        FibonacciCalculator c = mock(calculatorClass);
        FibonacciCalculator proxy = CacheProxy.create(c, c.getClass(), cacheDir);

        for (int i = 0; i < expectedResults.length; i++) {
            when(c.fib(i)).thenReturn(expectedResults[i]);

            assertThat(proxy.fib(i)).isEqualTo(expectedResults[i]);
            assertThat(proxy.fib(i)).isEqualTo(expectedResults[i]);
            assertThat(proxy.fib(i)).isEqualTo(expectedResults[i]);
            assertThat(proxy.fib(i)).isEqualTo(expectedResults[i]);

            Mockito.verify(c, Mockito.times(1)).fib(i);
        }
    }

    @Test
    void getValuesFromDiskCache() {
        long[] expectedResults = {1, 1, 2, 3, 5, 8, 13, 21, 34};

        FibonacciCalculator c = mock(RecursiveFibonacciCalculator.class);
        FibonacciCalculator proxy = CacheProxy.create(c, c.getClass(), cacheDir);

        for (int i = 1; i < expectedResults.length; i++) {
            when(c.fib(i)).thenReturn(expectedResults[i]);
            assertThat(proxy.fib(i)).isEqualTo(expectedResults[i]);
            assertThat(proxy.fib(i)).isEqualTo(expectedResults[i]);
            assertThat(proxy.fib(i)).isEqualTo(expectedResults[i]);
            Mockito.verify(c, Mockito.times(1)).fib(i);
        }

        reset(c);

        FibonacciCalculator proxyNew = CacheProxy.create(c, c.getClass(), cacheDir);
        for (int i = 1; i < expectedResults.length; i++) {
            assertThat(proxyNew.fib(i)).isEqualTo(expectedResults[i]);
            Mockito.verify(c, Mockito.times(0)).fib(i);
        }

    }
}
