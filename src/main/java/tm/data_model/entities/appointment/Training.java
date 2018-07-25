package tm.data_model.entities.appointment;

import tm.data_model.entities.exercise.Exercise;
import tm.data_model.entities.player.Player;

import java.util.ArrayList;

public class Training extends Appointment {

    private TrainingAttributes trainingAttributes;
    private final ArrayList<Exercise> exercises = new ArrayList<>();
    private final ArrayList<Player> players = new ArrayList<>();

    public Training(TrainingAttributes trainingAttributes) {
        this.trainingAttributes = trainingAttributes;
    }

    public TrainingAttributes getTrainingAttributes() {
        return trainingAttributes;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
