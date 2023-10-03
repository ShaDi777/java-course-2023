package edu.hw1;


public final class Task4 {
    private Task4() {
    }

    public static String fixString(String brokenString) {
        char[] chars = new char[brokenString.length()];
        int i = 0;
        for (; i < brokenString.length() - 1; i += 2) {
            chars[i] = brokenString.charAt(i + 1);
            chars[i + 1] = brokenString.charAt(i);
        }
        if (i < brokenString.length()) {
            chars[i] = brokenString.charAt(i);
        }

        return String.valueOf(chars);
    }
}
