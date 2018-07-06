package nr;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import nr.data_model.entities.player.Player;
import nr.data_model.entities.player.PlayerAttributes;
import nr.data_model.form_fields.BirthDate;
import nr.data_model.form_fields.PlayerName;
import nr.data_model.form_fields.Position;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;


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

        TreeSet<Position> positions1 = new TreeSet<>();
        positions1.add(Position.IV);

        players.add(new Player(new PlayerAttributes(new PlayerName("Herbert", "Harry"), new BirthDate(LocalDate.of(1993, 3, 18)), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Ben", "Loris"), new BirthDate(LocalDate.of(1993, 3, 18)), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Dieter", "Bens"), new BirthDate(LocalDate.of(1993, 3, 18)), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Floyd", "Teuchert"), new BirthDate(LocalDate.of(1993, 3, 18)), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Dancil", "Harrison"), new BirthDate(LocalDate.of(1993, 3, 18)), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Lucas", "Harry"), new BirthDate(LocalDate.of(1993, 3, 18)), positions1)));
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

        Button button = new Button("Click");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listView.getItems().add(new Player(new PlayerAttributes(new PlayerName("Been", "Loris"), new BirthDate(LocalDate.of(1993, 3, 18)), positions1)));
                listView.setCellFactory(new Callback<ListView<Player>, ListCell<Player>>() {
                    @Override
                    public ListCell<Player> call(ListView<Player> studentListView) {
                        return new PlayerListCell();
                    }
                });
                for (Player player : observedPlayers) {
                    System.out.println(player.getPlayerAttributes().getPlayerName().getNameString());
                }

            }
        });
        tab2.setContent(button);

        tabPane.getTabs().addAll(tab, tab2);

        Scene scene = new Scene(tabPane);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
