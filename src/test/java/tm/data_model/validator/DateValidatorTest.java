package tm.data_model.validator;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateValidatorTest {

    private static final LocalDate TOO_OLD_BIRTHDATE = LocalDate.MIN;
    private static final LocalDate DATE_IN_FUTURE = LocalDate.MAX;
    private static final LocalDateTime DATE_TIME_IN_FUTURE = LocalDateTime.MAX;
    private static final LocalDate VALID_BIRTHDATE = LocalDate.of(2000,1,1);

    @Test
    public void isValidBirthDate() {
    }

    @Test
    public void isInFuture() {
    }

    @Test
    public void isInFuture1() {
    }

    @Test
    public void isToOld() {
    }
}