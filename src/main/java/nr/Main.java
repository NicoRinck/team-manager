package nr;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;

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

        players.add(new Player(new PlayerAttributes(new PlayerName("Herbert", "Harry"),LocalDate.of(1993, 3, 18), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Ben", "Loris"), LocalDate.of(1993, 3, 18), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Dieter", "Bens"), LocalDate.of(1993, 3, 18), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Floyd", "Teuchert"), LocalDate.of(1993, 3, 18), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Dancil", "Harrison"), LocalDate.of(1993, 3, 18), positions1)));
        players.add(new Player(new PlayerAttributes(new PlayerName("Lucas", "Harry"), LocalDate.of(1993, 3, 18), positions1)));
        ObservableList<Player> observedPlayers = FXCollections.observableArrayList();
        observedPlayers.addAll(players);
        ListView<Player> listView = new ListView<>(observedPlayers);

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

        tabPane.getTabs().addAll(tab, tab2);
        Scene scene = new Scene(tabPane);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
