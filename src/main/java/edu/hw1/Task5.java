package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task5 {
    private final static Logger LOGGER = LogManager.getLogger();

    private static final int INTEGER_BASE = 10;

    private Task5() {
    }

    public static boolean isPalindromeDescendant(long n) {
        long num = n;
        while (num >= INTEGER_BASE) {
            LOGGER.trace("NUM: " + num);
            String numString = String.valueOf(num);
            if (isPalindrome(numString)) {
                return true;
            }
            num = getDescendant(numString);
        }
        return false;
    }

    private static boolean isPalindrome(String num) {
        return num.equals(new StringBuilder(num).reverse().toString());
    }

    private static long getDescendant(String num) {
        long result = 0;
        int i = 0;
        for (; i < num.length() - 1; i += 2) {
            int digit = num.charAt(i) - '0' + num.charAt(i + 1) - '0';
            if (digit < INTEGER_BASE) {
                // Shift by 1 digit left and add new digit
                result = result * INTEGER_BASE + digit;
            } else {
                // Shift by 2 digit left and add new digits
                result = result * INTEGER_BASE * INTEGER_BASE + digit;
            }
        }
        if (i < num.length()) {
            result = result * INTEGER_BASE + num.charAt(i) - '0';
        }
        return result;
    }
}
