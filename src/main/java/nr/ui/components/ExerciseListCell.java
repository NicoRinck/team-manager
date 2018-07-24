package nr.ui.components;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import nr.data_model.entities.exercise.Exercise;

public class ExerciseListCell extends ListCell<Exercise> implements ListItemStrategy<Exercise> {

    private HBox hBox = new HBox();
    private Label duration = new Label();
    private Label exerciseName = new Label(" Training");

    public ExerciseListCell() {
        hBox.setSpacing(5);
        hBox.getChildren().addAll(duration, exerciseName);
        hBox.setId("h-box");
        exerciseName.setId("name-label");
        hBox.getStylesheets().add("/css/ExerciseListItem.css");
    }

    @Override
    public ListCell<Exercise> getStrategy() {
        return new ExerciseListCell();
    }

    @Override
    protected void updateItem(Exercise item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {

            setText(null);
            setGraphic(null);

        } else {
            duration.setText(item.getExerciseAttributes().getAppointmentDuration().getDurationString());
            exerciseName.setText(item.getExerciseAttributes().getExerciseName());
            setText(null);
            setGraphic(hBox);
        }
    }
}
