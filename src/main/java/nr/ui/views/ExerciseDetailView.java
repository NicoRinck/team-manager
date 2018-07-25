package nr.ui.views;

import javafx.geometry.Pos;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import nr.data_model.entities.exercise.Exercise;
import nr.data_model.entities.exercise.ExerciseAttributes;

public class ExerciseDetailView implements EntityDetailView<Exercise> {

    private final Dialog<Exercise> dialog = new Dialog<>();
    private final Label exerciseName = new Label();
    private final Label exerciseDuration = new Label();
    private final Label exerciseDescription = new Label();
    private final VBox vBox = new VBox();

    public ExerciseDetailView() {
        initDialog();
    }

    private void initDialog() {
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Übung");
        Window window = dialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(event -> window.hide());
        initVBox();
        dialog.getDialogPane().setContent(vBox);
    }

    private void initVBox() {
        vBox.setSpacing(20);
        vBox.setPrefWidth(375);
        vBox.setPrefHeight(500);
        GridPane gridPane= new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(new Label("Bezeichung: "),0,0);
        gridPane.add(exerciseName,1,0);
        gridPane.add(new Label("Dauer: "),0,1);
        gridPane.add(exerciseDuration,1,1);
        Label header = new Label("Beschreibung:");
        header.setStyle("-fx-font-weight: bold; -fx-underline: true");
        initExerciseDescription();
        vBox.getChildren().addAll(gridPane,header, exerciseDescription);
    }

    private void initExerciseDescription() {
        exerciseDescription.setWrapText(true);
        exerciseDescription.setAlignment(Pos.TOP_LEFT);
        exerciseDescription.setPrefWidth(vBox.getPrefWidth());
        exerciseDescription.setMinHeight(200);
        exerciseDescription.setStyle("" +
                "-fx-background-color: white;" +
                " -fx-border-color: gray;" +
                "-fx-padding: 5");
    }

    @Override
    public void showDetailView(Exercise entity) {
        fillFields(entity.getExerciseAttributes());
        dialog.show();
    }

    private void fillFields(ExerciseAttributes exerciseAttributes) {
        exerciseName.setText(exerciseAttributes.getExerciseName());
        exerciseDuration.setText(exerciseAttributes.getAppointmentDuration().getDurationString());
        exerciseDescription.setText(exerciseAttributes.getDescription());
    }

    @Override
    public void updateDetailView() {

    }
}
