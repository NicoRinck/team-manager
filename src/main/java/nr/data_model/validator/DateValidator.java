package nr.data_model.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateValidator {

    public static String getErrorMessage(LocalDate birthDate) {
        if (isInFuture(birthDate)) {
            return "Datum liegt in der\nZukunft!";
        }
        if (isToOld(birthDate)) {
            return "Datum liegt zu weit\nin der Vergangenheit!";
        }
        return "";
    }
    public static boolean isValidBirthDate(LocalDate birthDate) {
        return !isToOld(birthDate) && !isInFuture(birthDate);
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static LocalDateTime getLocaleDateTime() {
        return LocalDateTime.now();
    }

    public static boolean isInFuture(LocalDate localDate) {
        return localDate.isAfter(getCurrentDate());
    }
    public static boolean isInFuture(LocalDateTime localDateTime) {
        return localDateTime.isAfter(getLocaleDateTime());
    }

    public static boolean isToOld(LocalDate birthDate) {
        return birthDate.getYear() < 1920;
    }
}
