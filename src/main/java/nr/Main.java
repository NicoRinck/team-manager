package nr;

import javafx.application.Application;
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
import nr.data_model.entities.player.Player;
import nr.data_model.entities.player.PlayerAttributes;
import nr.data_model.form_fields.BirthDate;
import nr.data_model.form_fields.PlayerName;
import nr.data_model.form_fields.address.AddressBuilder;
import nr.data_model.form_fields.position.PlayerPositions;
import nr.data_model.form_fields.position.Position;
import nr.ui.components.EntityList;
import nr.ui.components.PlayerListCell;
import nr.ui.event_handler.implementations.AddPlayerHandler;
import nr.ui.event_handler.implementations.DeleteEntityHandler;
import nr.ui.event_handler.implementations.EditPlayerHandler;
import nr.ui.event_handler.implementations.OpenDetailsHandler;
import nr.ui.forms.PlayerForm;
import nr.ui.views.EntityListView;
import nr.ui.views.PlayerDetailView;

import java.time.LocalDate;
import java.util.ArrayList;


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
                new BirthDate(LocalDate.of(1993, 3, 18)), new PlayerPositions(Position.LF))));
        players.add(new Player(new PlayerAttributes(new PlayerName("Dieter", "Bens"),
                new BirthDate(LocalDate.of(1993, 3, 18)), new PlayerPositions(Position.LIB))));
        players.add(new Player(new PlayerAttributes(new PlayerName("Floyd", "Teuchert"),
                new BirthDate(LocalDate.of(1993, 3, 18)), new PlayerPositions(Position.ZDM))));
        players.add(new Player(new PlayerAttributes(new PlayerName("Dancil", "Harrison"),
                new BirthDate(LocalDate.of(1993, 3, 18)), new PlayerPositions(Position.TW))));
        players.add(new Player(new PlayerAttributes(new PlayerName("Lucas", "Harry"),
                new BirthDate(LocalDate.of(1993, 3, 18)), new PlayerPositions(Position.LM))));
       /* observedPlayers.addListener(new ListChangeListener<Player>() {
            @Override
            public void onChanged(Change<? extends Player> c) {
                System.out.println("im am callled");

            }
        });*/

        Player player1 = new Player(new PlayerAttributes(new PlayerName("Ben", "Loris"), new BirthDate(LocalDate.of(1993, 3, 18)), positions1));
        Player player2 = new Player(new PlayerAttributes(new PlayerName("Dieter", "Bens"), new BirthDate(LocalDate.of(1993, 3, 18)), positions1));
        Player player3 = new Player(new PlayerAttributes(new PlayerName("Dieter", "Bens"), new BirthDate(LocalDate.of(1993, 3, 18)), positions1));
        player1.getPlayerAttributes().setAddress(AddressBuilder.residence("Schaidt").build());

        //erstmaliges befüllen
        /*H2DatabaseConnection h2DatabaseConnection = new H2DatabaseConnection();
        JsonDatabaseStrategy<Player> jsonDatabaseStrategy1 = new JsonDatabaseStrategy<>(h2DatabaseConnection,Player.class);
        jsonDatabaseStrategy1.saveEntity(player1);
        jsonDatabaseStrategy1.saveEntity(player2);
        jsonDatabaseStrategy1.saveEntity(player3);
        h2DatabaseConnection.closeConnection();*/

        DatabaseConnection databaseConnection = new H2DatabaseConnection();
        JsonDatabaseStrategy<Player> jsonDatabaseStrategy = new JsonDatabaseStrategy<>(databaseConnection, Player.class);
        DataManager<Player> dataManager = new DataManager<>(jsonDatabaseStrategy);
        EntityList<Player> playerEntityList = new EntityList<>(dataManager.getEntities(), new PlayerListCell(),
                new OpenDetailsHandler<>(new PlayerDetailView(new EditPlayerHandler(dataManager, new PlayerForm()),new DeleteEntityHandler<>(dataManager)), dataManager));


        EntityListView<Player> entityListView = new EntityListView<>(playerEntityList, new Button("Spieler hinzufügen..."),
                new AddPlayerHandler(new PlayerForm(), dataManager));

        Tab tab = new Tab();
        Tab tab2 = new Tab();

        tab.setText("new tab");
        tab.setContent(entityListView.getComponent());

        tab2.setText("new tab");
        tab2.setContent(new Rectangle(200, 200, Color.ROSYBROWN));

        HBox hBox = new HBox();

        Button button = new Button("Click");
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        /*PlayerAttributes playerAttributes = new PlayerAttributes(new PlayerName("Herbert", "Harry"),
                new BirthDate(LocalDate.of(1993, 3, 18)),
                positions1);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PlayerForm playerForm = new PlayerForm();
                playerForm.showCreateAttributesForm();
            }
        });*/
        hBox.getChildren().addAll(button, datePicker);
        tab2.setContent(hBox);

        tabPane.getTabs().addAll(tab, tab2);

        Scene scene = new Scene(tabPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}