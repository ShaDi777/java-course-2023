package edu.hw3;

import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @Test
    void nullComparatorTest() {
        TreeMap<String, String> tree = new TreeMap<>(new Task7.NullComparator<>());

        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
        assertThat(tree.get(null)).isEqualTo("test");
    }

    @Test
    void addNullTwiceTest() {
        TreeMap<String, String> tree = new TreeMap<>(new Task7.NullComparator<>());

        tree.put(null, "test1");
        tree.put(null, "test2");

        assertThat(tree.containsKey(null)).isTrue();
        assertThat(tree.get(null)).isEqualTo("test2");
    }

    @Test
    void addNullAfterStringsTest() {
        TreeMap<String, String> tree = new TreeMap<>(new Task7.NullComparator<>());

        tree.put("String1", "test1");
        tree.put("String2", "test2");
        tree.put("String3", "test3");
        tree.put(null, "test");
        tree.put("String4", "test4");
        tree.put("String1", "test5");

        assertThat(tree.containsKey(null)).isTrue();
        assertThat(tree.get(null)).isEqualTo("test");
        assertThat(tree.get("String1")).isEqualTo("test5");
    }
}
