package nr.data_model.validator;

import java.time.LocalDate;

public class BirthDateValidator {

    public static String getErrorMessage(LocalDate birthDate) {
        if (isInFuture(birthDate)) {
            return "Datum liegt in der Zukunft";
        }
        if (isToOld(birthDate)) {
            return "Datum liegt zu weit in der Vergangenheit";
        }
        return "";
    }
    public static boolean isValidBirthDate(LocalDate birthDate) {
        return !isToOld(birthDate) && !isInFuture(birthDate);
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static boolean isInFuture(LocalDate birthDate) {
        return birthDate.isAfter(getCurrentDate());
    }

    public static boolean isToOld(LocalDate birthDate) {
        return birthDate.getYear() < 1920;
    }
}
