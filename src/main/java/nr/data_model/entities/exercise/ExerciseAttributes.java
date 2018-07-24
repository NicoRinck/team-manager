package nr.data_model.entities.exercise;

import nr.data_model.entities.EntityAttributes;
import nr.data_model.form_fields.AppointmentDuration;
import nr.data_model.validator.NameStringValidator;

public class ExerciseAttributes implements EntityAttributes<Exercise> {

    private final String exerciseName;
    private final String description;
    private final AppointmentDuration appointmentDuration;

    public ExerciseAttributes(String exerciseName, String description, AppointmentDuration appointmentDuration) throws IllegalArgumentException {
        if (NameStringValidator.isValidNameString(exerciseName)) {
            this.exerciseName = exerciseName;
            this.description = description;
            this.appointmentDuration = appointmentDuration;
        } else throw new IllegalArgumentException();
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getDescription() {
        return description;
    }

    public AppointmentDuration getAppointmentDuration() {
        return appointmentDuration;
    }
}
