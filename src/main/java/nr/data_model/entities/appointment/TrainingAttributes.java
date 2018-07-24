package nr.data_model.entities.appointment;

import nr.data_model.entities.EntityAttributes;
import nr.data_model.form_fields.AppointmentDateTime;
import nr.data_model.form_fields.AppointmentDuration;
import nr.data_model.form_fields.address.Address;

public class TrainingAttributes implements EntityAttributes<Training> {

    private AppointmentDateTime dateTime;
    private AppointmentDuration duration;
    private Address address;

    public TrainingAttributes(AppointmentDateTime dateTime, AppointmentDuration duration) {
        this.dateTime = dateTime;
        this.duration = duration;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
