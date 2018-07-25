package tm.data_model.form_fields;

import tm.data_model.validator.DateValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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

    public String getDateTimeString() {
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT);
        return localDateTime.format(dateTimeFormatter);
    }
}
