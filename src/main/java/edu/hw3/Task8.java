package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public final class Task8 {
    private Task8() {
    }

    public static class BackwardIterator<T> implements Iterator<T> {
        private final ListIterator<T> iterator;

        public BackwardIterator(Collection<T> data) {
            this.iterator = data.stream().toList().listIterator(data.size());
        }

        @Override
        public boolean hasNext() {
            return iterator.hasPrevious();
        }

        @Override
        public T next() {
            return iterator.previous();
        }
    }
}
