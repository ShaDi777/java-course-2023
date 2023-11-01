package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task4 {
    private static final String SPECIAL_CHARS_REGEX = "[~!@#$%^&*|]";

    private Task4() {
    }

    public static boolean isValidPassword(String password) {
        Pattern specialCharsPattern = Pattern.compile(SPECIAL_CHARS_REGEX);
        Matcher matcher = specialCharsPattern.matcher(password);
        return matcher.find();
    }
}
