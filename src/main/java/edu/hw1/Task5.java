package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw1.Constants.INTEGER_BASE;

public final class Task5 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task5() {
    }

    public static boolean isPalindromeDescendant(long n) {
        long num = Math.abs(n);
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
        return num.contentEquals(new StringBuilder(num).reverse());
    }


    private static int digitCharToInt(char digit) {
        return digit - '0';
    }

    private static long getDescendant(String num) {
        long result = 0;
        int i = 0;
        for (; i < num.length() - 1; i += 2) {
            int digit = digitCharToInt(num.charAt(i)) + digitCharToInt(num.charAt(i + 1));
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
