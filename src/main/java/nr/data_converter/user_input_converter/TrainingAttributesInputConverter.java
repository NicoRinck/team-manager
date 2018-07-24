package nr.data_converter.user_input_converter;

import nr.data_model.entities.appointment.TrainingAttributes;
import nr.data_model.form_fields.AppointmentDateTime;
import nr.data_model.form_fields.AppointmentDuration;
import nr.data_model.form_fields.address.Address;
import nr.data_model.validator.DateValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public class TrainingAttributesInputConverter implements UserInputConverter<TrainingAttributes> {

    private AppointmentDateTime dateTime;
    private AppointmentDuration duration;
    private Address address;

    @Override
    public Optional<TrainingAttributes> convertInputToEntity() {
        if (dateTime != null && duration != null) {
            TrainingAttributes trainingAttributes = new TrainingAttributes(dateTime, duration);
            if (address != null) {
                trainingAttributes.setAddress(address);
            }
            return Optional.of(trainingAttributes);
        }
        return Optional.empty();
    }

    public boolean setDateTime(LocalDate localDate, String hoursString, String minutesString) {
        if (hoursString.length() > 0 && minutesString.length() > 0) {
            int hours = Integer.valueOf(hoursString);
            int minutes = Integer.valueOf(minutesString);
            LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(hours, minutes));
            if (DateValidator.isInFuture(localDateTime)) {
                dateTime = new AppointmentDateTime(localDateTime);
                return true;
            }
        }
        return false;
    }

    public boolean setDuration(String minimalTimeString, String maximalTimeString) {
        if (!maximalTimeString.equals("") && !minimalTimeString.equals("")) {
            int minimalTime = Integer.valueOf(minimalTimeString);
            int maximalTime = Integer.valueOf(maximalTimeString);
            if (minimalTime == maximalTime) {
                duration = new AppointmentDuration(minimalTime);
                return true;
            }
            if (minimalTime < maximalTime) {
                duration = new AppointmentDuration(minimalTime, maximalTime);
                return true;
            }
        }
        return false;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
