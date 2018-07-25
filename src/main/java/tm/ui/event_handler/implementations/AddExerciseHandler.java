package tm.ui.event_handler.implementations;

import tm.data_manager.DataManager;
import tm.data_model.entities.exercise.Exercise;
import tm.data_model.entities.exercise.ExerciseAttributes;
import tm.ui.components.EntityList;
import tm.ui.event_handler.AddEntityToListHandler;
import tm.ui.forms.Form;

import java.util.Optional;

public class AddExerciseHandler implements AddEntityToListHandler<Exercise> {

    final Form<ExerciseAttributes> exerciseForm;
    final DataManager<Exercise> dataManager;

    public AddExerciseHandler(Form<ExerciseAttributes> exerciseForm, DataManager<Exercise> dataManager) {
        this.exerciseForm = exerciseForm;
        this.dataManager = dataManager;
    }

    @Override
    public void addEntityToList(EntityList<Exercise> entityList) {
        final Optional<ExerciseAttributes> optional = exerciseForm.showCreateAttributesForm();

        optional.ifPresent(exerciseAttributes -> {
            Exercise exercise = new Exercise(exerciseAttributes);
            dataManager.saveEntity(exercise);
        });
    }
}
