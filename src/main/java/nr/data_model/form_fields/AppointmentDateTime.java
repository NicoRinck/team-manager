package nr.data_model.form_fields;

import nr.data_model.validator.DateValidator;

import java.time.LocalDateTime;

public class AppointmentDateTime {

    private final LocalDateTime localDateTime;

    public AppointmentDateTime(LocalDateTime localDateTime) throws IllegalArgumentException{
        if (DateValidator.isInFuture(localDateTime)) {
            this.localDateTime = localDateTime;
        } else throw new IllegalArgumentException();
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
