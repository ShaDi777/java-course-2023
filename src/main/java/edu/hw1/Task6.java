package edu.hw1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw1.Constants.INTEGER_BASE;

public final class Task6 {
    private final static Logger LOGGER = LogManager.getLogger();

    private static final int KAPREKAR_CONST = 6174;
    private static final int KAPREKAR_BOTTOM_RANGE_LIMIT = 1000;
    private static final int KAPREKAR_TOP_RANGE_LIMIT = 9999;
    private static final int KAPREKAR_COUNTER_LIMIT = 1_000;

    private Task6() {
    }

    public static int countK(int n) {
        if (!(KAPREKAR_BOTTOM_RANGE_LIMIT < n
            && n < KAPREKAR_TOP_RANGE_LIMIT
            && !hasSameDigits(n))) {
            return -1;
        }

        int counter = 0;
        int result = n;
        while (result != KAPREKAR_CONST) {
            if (counter >= KAPREKAR_COUNTER_LIMIT) {
                break;
            }

            char[] resultCharArray = String.valueOf(result).toCharArray();

            Arrays.sort(resultCharArray);
            int min = Integer.parseInt(String.valueOf(resultCharArray));

            for (int i = 0; i < resultCharArray.length / 2; i++) {
                char temp = resultCharArray[i];
                resultCharArray[i] = resultCharArray[resultCharArray.length - i - 1];
                resultCharArray[resultCharArray.length - i - 1] = temp;
            }
            int max = Integer.parseInt(String.valueOf(resultCharArray));

            result = max - min;
            LOGGER.trace("{} - {} = {}", max, min, result);
            counter++;
        }
        return counter;
    }

    private static boolean hasSameDigits(int n) {
        int num = n;
        int digit = num % INTEGER_BASE;
        while (num > 0) {
            if (digit != (num % INTEGER_BASE)) {
                return false;
            }
            num /= INTEGER_BASE;
        }
        return true;
    }
}
