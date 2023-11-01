package edu.hw5;

public final class Task7 {
    // Cодержит не менее 3 символов, причем третий символ равен 0
    private static final String REGEX_1 = "^[01]{2}0[01]*$";

    // Начинается и заканчивается одним и тем же символом
    private static final String REGEX_2 = "0|1|^0[01]*0$|^1[01]*1$";

    // Длина не менее 1 и не более 3
    private static final String REGEX_3 = "^[01]{1,3}$";

    private Task7() {
    }

    public static boolean moreThanThreeSymbolsThirdIsZero(String string) {
        return string.matches(REGEX_1);
    }

    public static boolean startsAndEndsWithSameSymbol(String string) {
        return string.matches(REGEX_2);
    }

    public static boolean lengthBetweenOneAndThree(String string) {
        return string.matches(REGEX_3);
    }
}
