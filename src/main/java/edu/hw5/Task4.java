package edu.hw5;

public final class Task4 {
    private static final String HAS_SPECIAL_CHARS_REGEX = ".*[~!@#$%^&*|].*";

    private Task4() {
    }

    public static boolean isValidPassword(String password) {
        String mustHaveChars = "";

        return password.matches(HAS_SPECIAL_CHARS_REGEX);
    }
}
