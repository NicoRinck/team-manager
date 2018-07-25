package tm.ui.event_handler.implementations;

import tm.data_manager.DataManager;
import tm.data_model.entities.appointment.Training;
import tm.data_model.entities.appointment.TrainingAttributes;
import tm.ui.components.EntityList;
import tm.ui.event_handler.AddEntityToListHandler;
import tm.ui.forms.Form;

import java.util.Optional;

public class AddTrainingHandler implements AddEntityToListHandler<Training> {

    private final Form<TrainingAttributes> trainingForm;
    private final DataManager<Training> dataManager;

    public AddTrainingHandler(Form<TrainingAttributes> trainingForm, DataManager<Training> dataManager) {
        this.trainingForm = trainingForm;
        this.dataManager = dataManager;
    }

    @Override
    public void addEntityToList(EntityList<Training> entityList) {
        final Optional<TrainingAttributes> optional = trainingForm.showCreateAttributesForm();

        optional.ifPresent(trainingAttributes -> {
            Training training = new Training(trainingAttributes);
            dataManager.saveEntity(training);
        });
    }
}
