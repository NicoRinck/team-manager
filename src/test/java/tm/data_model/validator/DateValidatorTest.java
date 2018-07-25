package tm.data_model.validator;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateValidatorTest {

    private static final LocalDate LONG_IN_PAST = LocalDate.MIN;
    private static final LocalDateTime DATE_TIME_LONG_IN_PAST = LocalDateTime.MIN;
    private static final LocalDate DATE_IN_FUTURE = LocalDate.MAX;
    private static final LocalDateTime DATE_TIME_IN_FUTURE = LocalDateTime.MAX;
    private static final LocalDate VALID_BIRTH_DATE = LocalDate.of(2000,1,1);

    @Test
    public void isValidBirthDate_validBirthDate() {
        boolean result = DateValidator.isValidBirthDate(VALID_BIRTH_DATE);
        assertTrue(result);
    }

    @Test
    public void isInFuture_longInPast() {
        boolean result = DateValidator.isInFuture(LONG_IN_PAST);
        assertFalse(result);
    }
    @Test
    public void isInFuture_dateInFuture() {
        boolean result = DateValidator.isInFuture(DATE_IN_FUTURE);
        assertTrue(result);
    }

    @Test
    public void isInFutureDateTime_dateInFuture() {
        boolean result = DateValidator.isInFuture(DATE_TIME_IN_FUTURE);
        assertTrue(result);
    }
    @Test
    public void isInFutureDateTime_dateTimeLongInPast() {
        boolean result = DateValidator.isInFuture(DATE_TIME_LONG_IN_PAST);
        assertFalse(result);
    }


    @Test
    public void isToOld_tooOldBirthDate() {
        boolean result = DateValidator.isToOld(LONG_IN_PAST);
        assertTrue(result);
    }
    @Test
    public void isToOld_validBirthDate() {
        boolean result = DateValidator.isToOld(VALID_BIRTH_DATE);
        assertFalse(result);
    }
}