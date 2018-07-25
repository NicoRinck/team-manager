package tm.data_model.validator;

public class DurationValidator {

    public static boolean validDurationBounds(Integer minimalTime,Integer maximalTime) {
        return isNaturalNumber(maximalTime) && isNaturalNumber(minimalTime) && minimalTime < maximalTime;
    }

    public static boolean isNaturalNumber(Integer integer) {
        return integer >= 0;
    }
}
