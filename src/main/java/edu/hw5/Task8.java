package edu.hw5;

public final class Task8 {
    // нечетной длины
    private final static String REGEX_1 = "^([01][01])*[01]$";

    // начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    private final static String REGEX_2 = "0([01][01])*|1([01][01])*[01]";

    // количество 0 кратно 3
    private final static String REGEX_3 = "^1*$|^(1*01*01*01*)*$";

    // любая строка, кроме 11 или 111
    private final static String REGEX_4 = "^(?!^111$|^11$)([01]*?)$";

    // каждый нечетный символ равен 1
    private final static String REGEX_5 = "^(1[01])*1{0,1}$";

    // содержит не менее двух 0 и не более одной 1
    private final static String REGEX_6 = "^0{2,}$|^0*(001|010|100)0*$";

    // нет последовательных 1
    private final static String REGEX_7 = "^1$|^(0+1{0,1})*$|^(1{0,1}0+)*$";

    private Task8() {
    }

    public static boolean lengthOdd(String string) {
        return string.matches(REGEX_1);
    }

    public static boolean zeroWithOddLengthOrOneWithEvenLength(String string) {
        return string.matches(REGEX_2);
    }

    public static boolean zeroCountDivisibleBy3(String string) {
        return string.matches(REGEX_3);
    }

    public static boolean not11or111(String string) {
        return string.matches(REGEX_4);
    }

    public static boolean everyOddPositionIs1(String string) {
        return string.matches(REGEX_5);
    }

    public static boolean twoAndMoreZerosWithOneAndLessOnes(String string) {
        return string.matches(REGEX_6);
    }

    public static boolean noConsecutiveOnes(String string) {
        return string.matches(REGEX_7);
    }
}
