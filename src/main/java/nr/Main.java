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
import nr.data_model.entities.player.Player;
import nr.data_model.entities.player.PlayerAttributes;
import nr.data_model.form_fields.BirthDate;
import nr.data_model.form_fields.PlayerName;
import nr.data_model.form_fields.position.PlayerPositions;
import nr.data_model.form_fields.position.Position;
import nr.ui.PlayerForm;
import nr.ui.components.EntityList;
import nr.ui.components.PlayerListCell;
import nr.ui.event_handler.AddEntityHandler;
import nr.ui.event_handler.OpenDetailsHandler;
import nr.ui.views.EntityListView;
import nr.ui.views.PlayerDetailView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


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
       /* observedPlayers.addListener(new ListChangeListener<Player>() {
            @Override
            public void onChanged(Change<? extends Player> c) {
                System.out.println("im am callled");

            }
        });*/
        HashMap<Integer,String> hashMap = new HashMap<>();
        hashMap.put(1,"1");
        hashMap.put(2,"2");
        hashMap.put(3,"3");
        hashMap.put(4,"4");

        hashMap.forEach((integer, s) -> System.out.println(integer + " --> " + s));

      /*  Player player1 = new Player(new PlayerAttributes(new PlayerName("Ben", "Loris"), new BirthDate(LocalDate.of(1993, 3, 18)), positions1));
        Player player2 = new Player(new PlayerAttributes(new PlayerName("Dieter", "Bens"), new BirthDate(LocalDate.of(1993, 3, 18)), positions1));
        Player player3 = new Player(new PlayerAttributes(new PlayerName("Dieter", "Bens"), new BirthDate(LocalDate.of(1993, 3, 18)), positions1));


        H2DatabaseConnection h2DatabaseConnection = new H2DatabaseConnection();
        JsonDatabaseStrategy<Player> jsonDatabaseStrategy = new JsonDatabaseStrategy<>(h2DatabaseConnection,Player.class);
        jsonDatabaseStrategy.saveEntity(player1);
        jsonDatabaseStrategy.saveEntity(player2);
        jsonDatabaseStrategy.saveEntity(player3);
        h2DatabaseConnection.closeConnection();

        H2DatabaseConnection h2DatabaseConnection1 = new H2DatabaseConnection();
        JsonDatabaseStrategy<Player> jsonDatabaseStrategy2 = new JsonDatabaseStrategy<>(h2DatabaseConnection1,Player.class);
        List<Player> players2 = jsonDatabaseStrategy2.getEntities();
        players2.forEach(player -> System.out.println(player.getPlayerAttributes().getPlayerName().getNameString()));*/





        EntityList<Player> entityList = new EntityList<>(players, new PlayerListCell(), new OpenDetailsHandler<Player>(new PlayerDetailView()));
        EntityListView<Player> entityListView = new EntityListView<>(entityList, new Button("add"), new AddEntityHandler<>());

        Tab tab = new Tab();
        Tab tab2 = new Tab();

        tab.setText("new tab");
        tab.setContent(entityListView.getView());

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