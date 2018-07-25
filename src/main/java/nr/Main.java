package nr;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import nr.data_access_layer.DatabaseConnection;
import nr.data_access_layer.H2DatabaseConnection;
import nr.data_access_layer.entity_database_strategy.JsonDatabaseStrategy;
import nr.data_manager.DataManager;
import nr.data_model.entities.appointment.Training;
import nr.data_model.entities.appointment.TrainingAttributes;
import nr.data_model.entities.exercise.Exercise;
import nr.data_model.entities.player.Player;
import nr.data_model.form_fields.position.PlayerPositions;
import nr.data_model.form_fields.position.Position;
import nr.ui.components.EntityList;
import nr.ui.components.ExerciseListCell;
import nr.ui.components.PlayerListCell;
import nr.ui.components.TrainingListCell;
import nr.ui.event_handler.implementations.*;
import nr.ui.forms.ExerciseForm;
import nr.ui.forms.PlayerForm;
import nr.ui.forms.TrainingForm;
import nr.ui.views.EntityListView;
import nr.ui.views.ExerciseDetailView;
import nr.ui.views.PlayerDetailView;
import nr.ui.views.TrainingDetailView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Test");
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        ArrayList<Player> players = new ArrayList<>();

        PlayerPositions positions1 = new PlayerPositions(Position.IV);


        DatabaseConnection databaseConnection = new H2DatabaseConnection();
        JsonDatabaseStrategy<Player> jsonDatabaseStrategy = new JsonDatabaseStrategy<>(databaseConnection, Player.class);
        DataManager<Player> dataManager = new DataManager<>(jsonDatabaseStrategy);
        EntityList<Player> playerEntityList = new EntityList<>(dataManager.getEntities(), new PlayerListCell(),
                new OpenDetailsHandler<>(new PlayerDetailView(new EditPlayerHandler(dataManager, new PlayerForm()),new DeleteEntityHandler<>(dataManager))));


        EntityListView<Player> entityListView = new EntityListView<>(playerEntityList, new Button("Spieler hinzufügen..."),
                new AddPlayerHandler(new PlayerForm(), dataManager));

        Tab tab = new Tab();
        Tab tab1 = new Tab();
        Tab tab2 = new Tab();
        Tab tab3 = new Tab();

        JsonDatabaseStrategy<Training> trainingJsonDatabaseStrategy = new JsonDatabaseStrategy<>(databaseConnection,Training.class);
        DataManager<Training> trainingDataManager = new DataManager<>(trainingJsonDatabaseStrategy);

        EntityList<Training> trainingEntityList = new EntityList<>(trainingDataManager.getEntities(),new TrainingListCell(),new OpenDetailsHandler<>(new TrainingDetailView()));
        EntityListView<Training> trainingEntityListView = new EntityListView<>(trainingEntityList, new Button("Training hinzufügen..."), new AddTrainingHandler(new TrainingForm(),trainingDataManager));

        tab.setText("new tab");
        tab.setContent(entityListView.getComponent());

        tab1.setText("Terminplan");
        tab1.setContent(trainingEntityListView.getComponent());

        tab2.setText("new tab");
        tab2.setContent(new Rectangle(200, 200, Color.ROSYBROWN));

        JsonDatabaseStrategy<Exercise> exerciseJsonDatabaseStrategy = new JsonDatabaseStrategy<>(databaseConnection,Exercise.class);
        DataManager<Exercise> exerciseDataManager = new DataManager<>(exerciseJsonDatabaseStrategy);
        EntityList<Exercise> exerciseEntityList = new EntityList<>(exerciseDataManager.getEntities(), new ExerciseListCell(),new OpenDetailsHandler<>(new ExerciseDetailView()));
        EntityListView<Exercise> exercisesView = new EntityListView<>(exerciseEntityList,new Button("Übung hinzufügen..."),new AddExerciseHandler(new ExerciseForm(),exerciseDataManager));

        tab3.setText("Übungen");
        tab3.setContent(exercisesView.getComponent());



        HBox hBox = new HBox();

        Button button = new Button("Click");
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TrainingForm trainingForm = new TrainingForm();
                Optional<TrainingAttributes> optional = trainingForm.showCreateAttributesForm();
                System.out.println(optional);
            }
        });
        hBox.getChildren().addAll(button, datePicker);
        tab2.setContent(hBox);

        tabPane.getTabs().addAll(tab,tab1, tab2, tab3);

        Scene scene = new Scene(tabPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}