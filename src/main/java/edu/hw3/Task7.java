package edu.hw3;

import java.util.Comparator;

public final class Task7 {
    private static final int LESS = -1;
    private static final int EQUAL = 0;
    private static final int GREATER = 1;

    private Task7() {
    }

    public static class NullComparator<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            if (o1 == null && o2 == null) {
                return EQUAL;
            }
            if (o1 == null) {
                return LESS;
            }
            if (o2 == null) {
                return GREATER;
            }
            return o1.compareTo(o2);
        }
    }
}
