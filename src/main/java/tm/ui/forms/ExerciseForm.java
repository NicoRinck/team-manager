package tm.ui.forms;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import tm.data_converter.user_input_converter.ExerciseAttributesInputConverter;
import tm.data_model.entities.exercise.ExerciseAttributes;
import tm.ui.components.form_components.DurationComponent;
import tm.ui.components.form_components.FormComponent;

import java.util.Optional;

public class ExerciseForm extends DialogForm<ExerciseAttributes> {

    private final GridPane gridPane = new GridPane();
    private DurationComponent durationComponent;
    private final TextField exerciseName = new TextField();
    private final TextArea exerciseDescription = new TextArea();
    private final Label exerciseNameError = new Label();
    private final Label descriptionError = new Label();

    public ExerciseForm() {
        initGrid();
        exerciseNameError.setStyle("-fx-text-fill: red");
    }

    private void initGrid() {
        this.gridPane.setVgap(10);
        this.gridPane.setHgap(10);
        this.gridPane.add(new Label("Name der Übung: "), 0, 0);
        this.gridPane.add(exerciseName, 1, 0);
        this.gridPane.add(exerciseNameError, 2, 0);
    }

    private void initForm() {
        initDialog();
        setVBoxContent();
    }

    private void setVBoxContent() {
        vBox.getChildren().clear();
        Label header = new Label("Beschreibung:");
        header.setStyle("-fx-font-weight: bold; -fx-underline: true");
        vBox.getChildren().addAll(gridPane, durationComponent.getComponent(), header, exerciseDescription, descriptionError);
    }

    @Override
    public Optional<ExerciseAttributes> showCreateAttributesForm() {
        this.durationComponent = new DurationComponent();
        initForm();
        this.dialog.setTitle("Übung erstellen");
        return showForm();
    }

    @Override
    public Optional<ExerciseAttributes> showEditAttributesForm(ExerciseAttributes entityAttributes) {
        this.durationComponent = new DurationComponent(entityAttributes.getAppointmentDuration());
        initForm();
        this.dialog.setTitle("Übung bearbeiten");
        return showForm();
    }

    @Override
    Optional<ExerciseAttributes> getConvertedInput() {
        ExerciseAttributesInputConverter inputConverter = new ExerciseAttributesInputConverter();
        if (!inputConverter.setExerciseName(exerciseName.getText().trim())) {
            FormComponent.markInvalidFields(exerciseName, exerciseNameError, "Name ist ungültig!");
        }
        if (!inputConverter.setExerciseDescription(exerciseDescription.getText())) {
            FormComponent.markInvalidFields(exerciseDescription, descriptionError, "Die Beschreibung ist zu lang!");
        }
        durationComponent.getFormComponentValue().ifPresent(inputConverter::setAppointmentDuration);
        return inputConverter.convertInputToEntity();
    }
}