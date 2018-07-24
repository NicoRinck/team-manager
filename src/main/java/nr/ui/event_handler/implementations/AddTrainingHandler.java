package nr.ui.event_handler.implementations;

import nr.data_manager.DataManager;
import nr.data_model.entities.appointment.Training;
import nr.data_model.entities.appointment.TrainingAttributes;
import nr.ui.components.EntityList;
import nr.ui.event_handler.AddEntityToListHandler;
import nr.ui.forms.Form;

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
