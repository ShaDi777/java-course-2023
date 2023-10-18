package edu.hw3;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class Task5 {
    private Task5() {
    }

    public enum ORDER {
        ASC, DESC
    }

    public static List<PersonLabel> parseContacts(Collection<PersonLabel> collection, ORDER order) {
        if (collection == null) {
            return List.of();
        }
        if (order == ORDER.ASC) {
            return collection.stream().sorted(Comparator.naturalOrder()).toList();
        }
        return collection.stream().sorted(Comparator.reverseOrder()).toList();
    }

    public record PersonLabel(String name, String surname) implements Comparable<PersonLabel> {
        @Override
        public int compareTo(@NotNull PersonLabel other) {
            if (this.surname == null || other.surname == null) {
                return this.name.compareTo(other.name);
            }
            return this.surname.compareTo(other.surname);
        }
    }
}
