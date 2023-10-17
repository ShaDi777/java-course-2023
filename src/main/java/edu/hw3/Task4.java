package edu.hw3;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

public final class Task4 {
    @SuppressWarnings("checkstyle:MagicNumber") // Roman constants
    private static final SortedMap<Integer, String> ROMAN_NUMERALS =
        new TreeMap<Integer, String>(Collections.reverseOrder()) {
            {
                put(1000, "M");
                put(900, "CM");
                put(500, "D");
                put(400, "CD");
                put(100, "C");
                put(90, "XC");
                put(50, "L");
                put(40, "XL");
                put(10, "X");
                put(9, "IX");
                put(5, "V");
                put(4, "IV");
                put(1, "I");
            }
        };
    private static final int MAX_ROMAN_NUM = 3999;

    private Task4() {
    }

    public static String convertToRoman(int num) {
        if (!(0 < num && num <= MAX_ROMAN_NUM)) {
            return null;
        }

        int n = num;
        StringBuilder sb = new StringBuilder();
        for (var kv : ROMAN_NUMERALS.entrySet()) {
            if (kv.getKey() > n) {
                continue;
            }
            while (n >= kv.getKey()) {
                n -= kv.getKey();
                sb.append(kv.getValue());
            }
        }

        return sb.toString();
    }
}
