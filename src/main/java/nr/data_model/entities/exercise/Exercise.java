package nr.data_model.entities.exercise;

import nr.data_model.entities.Entity;

public class Exercise implements Entity {

    private ExerciseAttributes exerciseAttributes;

    public Exercise(ExerciseAttributes exerciseAttributes) {
        this.exerciseAttributes = exerciseAttributes;
    }

    public ExerciseAttributes getExerciseAttributes() {
        return exerciseAttributes;
    }
}
