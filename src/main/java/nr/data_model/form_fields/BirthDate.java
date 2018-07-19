package nr.data_model.form_fields;

import nr.data_model.validator.BirthDateValidator;

import java.time.LocalDate;
import java.time.Period;

public class BirthDate {

    private final LocalDate birthDate;

    public BirthDate(LocalDate birthDate) throws IllegalArgumentException {
        if (BirthDateValidator.isValidBirthDate(birthDate)) {
            this.birthDate = birthDate;
        } else throw new IllegalArgumentException();
    }

    public int getAge() {
        Period period = Period.between(birthDate, BirthDateValidator.getCurrentDate());
        return period.getYears();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
