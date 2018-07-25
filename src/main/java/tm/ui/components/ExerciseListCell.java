package tm.ui.components;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import tm.data_model.entities.exercise.Exercise;

public class ExerciseListCell extends ListCell<Exercise> implements ListItemStrategy<Exercise> {

    private GridPane gridPane = new GridPane();
    private Label duration = new Label();
    private Label exerciseName = new Label();
    private final boolean variableHeight;

    public ExerciseListCell(boolean variableHeight) {
        this.variableHeight = variableHeight;
        init();
    }

    public ExerciseListCell() {
        variableHeight = false;
        init();
    }

    private void init() {
        gridPane.setHgap(5);
        gridPane.add(duration,0,0);
        gridPane.add(exerciseName,1,0);
        gridPane.setId("h-box");
        setColumnConstraints();
        exerciseName.setId("name-label");
        gridPane.getStylesheets().add("/css/ExerciseListItem.css");
    }

    private void setColumnConstraints() {
     ColumnConstraints column1 = new ColumnConstraints(130);
     ColumnConstraints column2 = new ColumnConstraints();
     column2.setHgrow(Priority.ALWAYS);
     column2.setMaxWidth(300);
     gridPane.getColumnConstraints().addAll(column1,column2);
    }

    private void setHeight(int height) {
        duration.setPrefHeight(height);
        exerciseName.setPrefHeight(height);
    }

    @Override
    public ListCell<Exercise> getStrategy() {
        if (variableHeight) {
            return new ExerciseListCell(true);
        }
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
            if (variableHeight) {
                setHeight(item.getExerciseAttributes().getAppointmentDuration().getMaximalOrExactTime());
            }
            setText(null);
            setGraphic(gridPane);
        }
    }
}
