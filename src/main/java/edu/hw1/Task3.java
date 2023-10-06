package edu.hw1;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

        long minArr1 = arr1[0];
        long maxArr1 = arr1[0];
        for (long elem : arr1) {
            minArr1 = Math.min(minArr1, elem);
            maxArr1 = Math.max(maxArr1, elem);
        }

        long minArr2 = arr2[0];
        long maxArr2 = arr2[0];
        for (long elem : arr2) {
            minArr2 = Math.min(minArr2, elem);
            maxArr2 = Math.max(maxArr2, elem);
        }

        return (minArr1 > minArr2) && (maxArr1 < maxArr2);
    }
}
