package nr.data_model.form_fields;

import java.time.LocalDate;
import java.time.Period;

public class BirthDate {

    private final LocalDate birthDate;

    public BirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public int getAge() {
        Period period = Period.between(birthDate, getCurrentDate());
        return period.getYears();
    }
}
