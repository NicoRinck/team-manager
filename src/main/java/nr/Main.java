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

import java.util.ArrayList;
import java.util.Date;


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
        players.add(new Player(new PlayerAttributes(new PlayerName("Herbert", "Harry"), new Date(1993,3,18),new Postition[]{Postition.IV})));
        players.add(new Player(new PlayerAttributes(new PlayerName("Ben", "Loris"), new Date(1971,3,8),new Postition[]{Postition.ST})));
        players.add(new Player(new PlayerAttributes(new PlayerName("Dieter", "Bens"), new Date(1912,3,20),new Postition[]{Postition.ZDM})));
        players.add(new Player(new PlayerAttributes(new PlayerName("Floyd", "Teuchert"), new Date(1984,3,18),new Postition[]{Postition.TW})));
        players.add(new Player(new PlayerAttributes(new PlayerName("Dancil", "Harrison"), new Date(1999,3,2),new Postition[]{Postition.RF})));
        players.add(new Player(new PlayerAttributes(new PlayerName("Lucas", "Harry"), new Date(1979,3,2),new Postition[]{Postition.LV})));

        ObservableList<Player> observedPlayers = FXCollections.observableArrayList();
        observedPlayers.addAll(players);
        ListView<Player> listView = new ListView<>(observedPlayers);

        listView.setCellFactory(new Callback<ListView<Player>, ListCell<Player>>() {
            @Override
            public ListCell<Player> call(ListView<Player> studentListView) {
                return new PlayerListCell();
            }
        });
        listView.setStyle("-fx-vgap: 0");

        Tab tab = new Tab();
        Tab tab2 = new Tab();

        tab.setText("new tab");
        tab.setContent(listView);

        tab2.setText("new tab");
        tab2.setContent(new Rectangle(200,200, Color.ROSYBROWN));

        tabPane.getTabs().addAll(tab,tab2);
        Scene scene = new Scene(tabPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
