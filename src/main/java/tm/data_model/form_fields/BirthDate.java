package tm.data_model.form_fields;

import tm.data_model.validator.DateValidator;

import java.time.LocalDate;
import java.time.Period;

public class BirthDate {

    private final LocalDate birthDate;

    public BirthDate(LocalDate birthDate) throws IllegalArgumentException {
        if (DateValidator.isValidBirthDate(birthDate)) {
            this.birthDate = birthDate;
        } else throw new IllegalArgumentException();
    }

    public int getAge() {
        Period period = Period.between(birthDate, DateValidator.getCurrentDate());
        return period.getYears();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
