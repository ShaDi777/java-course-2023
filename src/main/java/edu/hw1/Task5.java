package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class Task5 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task5() {
    }

    public static boolean isPalindromeDescendant(long num) {
        while (num >= 10) {
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
            if (digit <= 9) {
                result = result * 10 + digit;
            } else {
                result = result * 100 + digit;
            }
        }
        if (i < num.length()) {
            result = result * 10 + num.charAt(i) - '0';
        }
        return result;
    }
}
