package edu.hw3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    private static Stream<Arguments> paramsCollections() {
        return Stream.of(
            Arguments.of(new ArrayList<Integer>()),
            Arguments.of(new Vector<Integer>()),
            Arguments.of(new LinkedList<Integer>()),
            Arguments.of(new HashSet<Integer>()),
            Arguments.of(new LinkedHashSet<Integer>()),
            Arguments.of(new TreeSet<Integer>()),
            Arguments.of(new ArrayDeque<Integer>()),
            Arguments.of(new Stack<Integer>())
        );
    }

    @ParameterizedTest
    @MethodSource("paramsCollections")
    void backwardIteratorTest(Collection<Integer> collection) {
        collection.add(1);
        collection.add(2);
        collection.add(3);
        var backwardIterator = new Task8.BackwardIterator<Integer>(collection);

        assertThat(backwardIterator).isNotNull();
        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(3);
        assertThat(backwardIterator.next()).isEqualTo(2);
        assertThat(backwardIterator.next()).isEqualTo(1);
        assertThat(backwardIterator.hasNext()).isFalse();
    }
}
