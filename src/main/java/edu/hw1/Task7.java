package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task7 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task7() {
    }

    public static int rotateLeft(int n, int shiftDistance) {
        int bits = Integer.toBinaryString(n).length();
        int shift = shiftDistance % bits;

        return ((n << shift) | (n >> (bits - shift)))
            & (int) ((long) Math.pow(2, bits) - 1);
    }

    public static int rotateRight(int n, int shiftDistance) {
        int bits = Integer.toBinaryString(n).length();
        int shift = shiftDistance % bits;

        return ((n >> shift) | (n << (bits - shift)))
            & (int) ((long) Math.pow(2, bits) - 1);
    }
}
