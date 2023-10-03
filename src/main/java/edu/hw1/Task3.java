package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.Objects;

public final class Task3 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task3() {
    }

    public static boolean isNestable(long[] arr1, long[] arr2) {
        Objects.requireNonNull(arr1);
        Objects.requireNonNull(arr2);

        if (arr1.length == 0 && arr2.length == 0) {
            return false;
        } else if (arr1.length == 0) {
            return true;
        } else if (arr2.length == 0) {
            return false;
        }

        long minArr1 = Arrays.stream(arr1).min().getAsLong();
        long maxArr1 = Arrays.stream(arr1).max().getAsLong();

        long minArr2 = Arrays.stream(arr2).min().getAsLong();
        long maxArr2 = Arrays.stream(arr2).max().getAsLong();

        return (minArr1 > minArr2) && (maxArr1 < maxArr2);
    }
}
