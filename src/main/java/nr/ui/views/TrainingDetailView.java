package nr.ui.views;

import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import nr.data_model.entities.appointment.Training;
import nr.data_model.entities.appointment.TrainingAttributes;

import java.time.LocalDateTime;

public class TrainingDetailView implements EntityDetailView<Training> {

    private final Dialog<Training> trainingDialog = new Dialog<>();
    private final VBox vBox = new VBox();
    private final GridPane gridPane = new GridPane();
    private final Button addExercises = new Button("Übungen hinzufügen");

    public TrainingDetailView() {
        initView();
    }

    private void initView() {
        initGrid();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(gridPane);
        trainingDialog.getDialogPane().setContent(vBox);
    }

    private void fillGrid(TrainingAttributes trainingAttributes) {
        Label dateTime = new Label(trainingAttributes.getDateTime().getDateTimeString());
        Label duration = new Label(trainingAttributes.getDuration().getDurationString());
        Label address = new Label(EntityDetailView.getAddressString(trainingAttributes.getAddress()));
        gridPane.add(dateTime, 1, 0);
        gridPane.add(duration, 1, 1);
        gridPane.add(address, 0, 2);
    }

    private void initGrid() {
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(new Label("Termin: "), 0, 0);
        gridPane.add(new Label("Dauer:  "), 0, 1);
        gridPane.add(new Label("Adresse: "), 0, 2);
    }

    @Override
    public void showDetailView(Training entity) {
        buildView(entity);
        trainingDialog.show();
    }

    private void buildView(Training entity) {
        fillGrid(entity.getTrainingAttributes());
        if (!entity.getTrainingAttributes().getDateTime().getLocalDateTime().isAfter(LocalDateTime.now())) {
            //training nachbereiten
        } else {
            initBaseView();
        }
    }

    private void initBaseView() {
        System.out.println("called");
        vBox.getChildren().add(1, addExercises);
    }

    @Override
    public void updateDetailView() {

    }
}
