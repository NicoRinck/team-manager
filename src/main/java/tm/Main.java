package tm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import tm.data_access_layer.DatabaseConnection;
import tm.data_access_layer.H2DatabaseConnection;
import tm.data_access_layer.entity_database_strategy.JsonDatabaseStrategy;
import tm.data_manager.DataManager;
import tm.data_model.entities.appointment.Training;
import tm.data_model.entities.exercise.Exercise;
import tm.data_model.entities.player.Player;
import tm.ui.components.EntityList;
import tm.ui.components.ExerciseListCell;
import tm.ui.components.PlayerListCell;
import tm.ui.components.TrainingListCell;
import tm.ui.event_handler.implementations.*;
import tm.ui.forms.ExerciseForm;
import tm.ui.forms.PlayerForm;
import tm.ui.forms.TrainingForm;
import tm.ui.views.EntityListView;
import tm.ui.views.ExerciseDetailView;
import tm.ui.views.PlayerDetailView;
import tm.ui.views.TrainingDetailView;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Team-Manager");
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        DatabaseConnection databaseConnection = new H2DatabaseConnection();

        JsonDatabaseStrategy<Player> jsonDatabaseStrategy = new JsonDatabaseStrategy<>(databaseConnection, Player.class);
        DataManager<Player> playerDataManager = new DataManager<>(jsonDatabaseStrategy);
        EntityList<Player> playerEntityList = new EntityList<>(
                playerDataManager.getEntities(),
                new PlayerListCell(),
                new OpenDetailsHandler<>(
                        new PlayerDetailView(
                                new EditPlayerHandler(playerDataManager, new PlayerForm()),
                                new DeleteEntityHandler<>(playerDataManager))));

        EntityListView<Player> entityListView = new EntityListView<>(playerEntityList, new Button("Spieler hinzufügen..."),
                new AddPlayerHandler(new PlayerForm(), playerDataManager));


        JsonDatabaseStrategy<Training> trainingJsonDatabaseStrategy = new JsonDatabaseStrategy<>(databaseConnection, Training.class);
        DataManager<Training> trainingDataManager = new DataManager<>(trainingJsonDatabaseStrategy);

        JsonDatabaseStrategy<Exercise> exerciseJsonDatabaseStrategy = new JsonDatabaseStrategy<>(databaseConnection, Exercise.class);
        DataManager<Exercise> exerciseDataManager = new DataManager<>(exerciseJsonDatabaseStrategy);

        EntityList<Exercise> exerciseEntityList = new EntityList<>(
                exerciseDataManager.getEntities(),
                new ExerciseListCell(),
                new OpenDetailsHandler<>(new ExerciseDetailView()));

        EntityListView<Exercise> exercisesView = new EntityListView<>(
                exerciseEntityList,
                new Button("Übung hinzufügen..."),
                new AddExerciseHandler(new ExerciseForm(), exerciseDataManager));


        EntityList<Training> trainingEntityList = new EntityList<>(
                trainingDataManager.getEntities(),
                new TrainingListCell(),
                new OpenDetailsHandler<>(new TrainingDetailView(
                        new OpenSelectionListHandler<>(exerciseDataManager.getEntities(), new ExerciseListCell(), false),
                        new OpenSelectionListHandler<>(playerDataManager.getEntities(), new PlayerListCell(), true))));
        EntityListView<Training> trainingEntityListView = new EntityListView<>(trainingEntityList, new Button("Training hinzufügen..."), new AddTrainingHandler(new TrainingForm(), trainingDataManager));

        Tab tab = new Tab();
        Tab tab1 = new Tab();
        Tab tab2 = new Tab();

        tab.setText("Spieler");
        tab.setContent(entityListView.getComponent());

        tab1.setText("Terminplan");
        tab1.setContent(trainingEntityListView.getComponent());

        tab2.setText("Übungen");
        tab2.setContent(exercisesView.getComponent());

        tabPane.getTabs().addAll(tab, tab1, tab2);

        Scene scene = new Scene(tabPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}