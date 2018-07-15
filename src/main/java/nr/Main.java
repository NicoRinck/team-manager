package nr;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import nr.data_model.entities.player.Player;
import nr.data_model.entities.player.PlayerAttributes;
import nr.data_model.form_fields.BirthDate;
import nr.data_model.form_fields.PlayerName;
import nr.data_model.form_fields.position.PlayerPositions;
import nr.data_model.form_fields.position.Position;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Hello world!
 */
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

        players.add(new Player(new PlayerAttributes(new PlayerName("Herbert", "Harry"),
                new BirthDate(LocalDate.of(1993, 3, 18)),
                positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Ben", "Loris"),
                new BirthDate(LocalDate.of(1993, 3, 18)), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Dieter", "Bens"),
                new BirthDate(LocalDate.of(1993, 3, 18)), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Floyd", "Teuchert"),
                new BirthDate(LocalDate.of(1993, 3, 18)), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Dancil", "Harrison"),
                new BirthDate(LocalDate.of(1993, 3, 18)), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Lucas", "Harry"),
                new BirthDate(LocalDate.of(1993, 3, 18)), positions1)));
        final ObservableList<Player> observedPlayers = FXCollections.observableArrayList();
        observedPlayers.addAll(players);
        observedPlayers.addListener(new ListChangeListener<Player>() {
            @Override
            public void onChanged(Change<? extends Player> c) {
                System.out.println("im am callled");

            }
        });
        final ListView<Player> listView = new ListView<>(observedPlayers);

        listView.setCellFactory(new Callback<ListView<Player>, ListCell<Player>>() {
            @Override
            public ListCell<Player> call(ListView<Player> studentListView) {
                return new PlayerListCell();
            }
        });

        Tab tab = new Tab();
        Tab tab2 = new Tab();

        tab.setText("new tab");
        tab.setContent(listView);

        tab2.setText("new tab");
        tab2.setContent(new Rectangle(200, 200, Color.ROSYBROWN));

        HBox hBox = new HBox();

        Button button = new Button("Click");
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        PlayerAttributes playerAttributes = new PlayerAttributes(new PlayerName("Herbert", "Harry"),
                new BirthDate(LocalDate.of(1993, 3, 18)),
                positions1);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PlayerForm playerForm = new PlayerForm(playerAttributes);
                playerForm.showForm();
            }
        });
        hBox.getChildren().addAll(button, datePicker);
        tab2.setContent(hBox);

        tabPane.getTabs().addAll(tab, tab2);

        Scene scene = new Scene(tabPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}