package nr.data_converter.user_input_converter;

import nr.data_model.entities.exercise.ExerciseAttributes;
import nr.data_model.form_fields.AppointmentDuration;
import nr.data_model.validator.NameStringValidator;

import java.util.Optional;

public class ExerciseAttributesInputConverter implements UserInputConverter<ExerciseAttributes> {

    private static int MAXIMAL_CHARACTERS = 1000;
    private String exerciseName = "";
    private AppointmentDuration appointmentDuration;
    private String exerciseDescription = "";

    @Override
    public Optional<ExerciseAttributes> convertInputToEntity() {
        if (!exerciseName.equals("") && appointmentDuration != null && !exerciseDescription.equals("")) {
            return Optional.of(new ExerciseAttributes(exerciseName,exerciseDescription,appointmentDuration));
        }
        return Optional.empty();
    }

    public boolean setExerciseName(String exerciseName) {
        if (NameStringValidator.isValidNameString(exerciseName)) {
            this.exerciseName = exerciseName;
            return true;
        }
        return false;
    }

    public void setAppointmentDuration(AppointmentDuration appointmentDuration) {
        this.appointmentDuration = appointmentDuration;
    }

    public boolean setExerciseDescription(String exerciseDescription) {
        if (exerciseDescription.length() < MAXIMAL_CHARACTERS) {
            this.exerciseDescription = exerciseDescription;
            return true;
        }
        return false;
    }
}
