package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Task3 {
    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> objects) {
        Map<T, Integer> frequencyMap = new HashMap<>();
        if (objects == null) {
            return frequencyMap;
        }
        objects.forEach(obj -> frequencyMap.merge(obj, 1, Integer::sum));

        return frequencyMap;
    }
}
