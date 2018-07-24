package nr.data_model.entities.appointment;

public class Training extends Appointment {

    private TrainingAttributes trainingAttributes;

    public Training(TrainingAttributes trainingAttributes) {
        this.trainingAttributes = trainingAttributes;
    }

    public TrainingAttributes getTrainingAttributes() {
        return trainingAttributes;
    }
}
