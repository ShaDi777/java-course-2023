package edu.hw5;

public final class Task5 {
    private static final String RUSSIAN_LICENSE_PLATE_REGEX =
        "[ABCEHKMOPTXY]\\d{3}[ABCEHKMOPTXY]{2}(\\d{2}|[1279]\\d{2})";

    private Task5() {
    }

    public static boolean isValidRussianLicensePlate(String licensePlate) {
        return licensePlate.matches(RUSSIAN_LICENSE_PLATE_REGEX);
    }
}
