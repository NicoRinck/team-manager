package tm.ui.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import tm.data_model.entities.appointment.Training;
import tm.data_model.entities.appointment.TrainingAttributes;
import tm.data_model.entities.exercise.Exercise;
import tm.data_model.entities.player.Player;
import tm.ui.components.EntityList;
import tm.ui.components.ExerciseListCell;
import tm.ui.components.PlayerListCell;
import tm.ui.event_handler.implementations.OpenDetailsHandler;
import tm.ui.event_handler.implementations.OpenSelectionListHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TrainingDetailView implements EntityDetailView<Training> {

    private final Dialog<Training> trainingDialog = new Dialog<>();
    private final VBox vBox = new VBox();
    private final GridPane gridPane = new GridPane();
    private final Button addExercises = new Button("Übungen hinzufügen...");
    private final Button addPlayers = new Button("Teilnehmende Spieler...");
    private final OpenSelectionListHandler<Exercise> selectExercises;
    private final OpenSelectionListHandler<Player> selectPlayers;

    public TrainingDetailView(OpenSelectionListHandler<Exercise> selectExercises,OpenSelectionListHandler<Player> selectPlayers) {
        this.selectExercises = selectExercises;
        this.selectPlayers = selectPlayers;
        initView();
    }

    private void initView() {
        initGrid();
        trainingDialog.initStyle(StageStyle.UTILITY);
        Window window = trainingDialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(event -> window.hide());
        vBox.setSpacing(20);
        vBox.setPrefWidth(375);
        vBox.setPrefHeight(500);
        vBox.getChildren().addAll(gridPane);
        trainingDialog.getDialogPane().setContent(vBox);
    }

    private void fillGrid(TrainingAttributes trainingAttributes) {
        Label dateTime = new Label(trainingAttributes.getDateTime().getDateTimeString());
        Label duration = new Label(trainingAttributes.getDuration().getDurationString());
        Label address = new Label(EntityDetailView.getAddressString(trainingAttributes.getAddress()));
        gridPane.add(dateTime, 1, 0);
        gridPane.add(duration, 1, 1);
        gridPane.add(address, 1, 2);
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

    private void buildView(Training training) {
        fillGrid(training.getTrainingAttributes());
        if (!training.getTrainingAttributes().getDateTime().getLocalDateTime().isAfter(LocalDateTime.now())) {
            initAfterView(training);
        } else {
            initBaseView(training);
        }
    }

    private void initAfterView(Training training) {
        final ObservableList<Player> players = FXCollections.observableArrayList(training.getPlayers());
        final EntityList<Player> playerComponent = new EntityList<>(
                players,
                new PlayerListCell(),
                new OpenDetailsHandler<>(new PlayerDetailView(null,null)));
        addPlayers.setOnMouseClicked(event -> {
            final ArrayList<Player> arrayList = selectPlayers.getEntitiesFromSelectionList();
            for (Player player : arrayList) {
                if (!players.contains(player)) {
                    players.add(player);
                    training.getPlayers().add(player);
                }
            }
        });
        vBox.getChildren().clear();
        vBox.getChildren().addAll(gridPane,addPlayers,playerComponent.getComponent());
    }

    private void initBaseView(Training training) {
        final ObservableList<Exercise> exercises = FXCollections.observableArrayList(training.getExercises());
        final EntityList<Exercise> exerciseComponent = new EntityList<>(
                exercises,
                new ExerciseListCell(true),
                new OpenDetailsHandler<>(new ExerciseDetailView()));
        addExercises.setOnMouseClicked(event -> {
            final ArrayList<Exercise> arrayList = selectExercises.getEntitiesFromSelectionList();
            for (Exercise exercise : arrayList) {
                if (!exercises.contains(exercise)) {
                    exercises.add(exercise);
                    training.getExercises().add(exercise);
                }
            }
        });
        vBox.getChildren().add(1, addExercises);
        vBox.getChildren().add(2, exerciseComponent.getComponent());
    }

    @Override
    public void updateDetailView() {
    }
}
