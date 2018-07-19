package nr.ui.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import nr.data_model.entities.player.Player;

import java.io.IOException;

public class PlayerListCell extends ListCell<Player> implements ListItemStrategy<Player> {

    private FXMLLoader loader = null;
    @FXML
    private Label playerName;
    @FXML
    private Label position;
    @FXML
    private Label age;
    @FXML
    private GridPane gridPane;

    @Override
    public ListCell<Player> getStrategy() {
        return new PlayerListCell();
    }

    @Override
    protected void updateItem(Player item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (loader == null) {
                loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/PlayerListItem.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                playerName.setText("  " + item.getPlayerAttributes().getPlayerName().getNameString());
                age.setText(item.getPlayerAttributes().getBirthDate().getAge() + "");
                position.setText(item.getPlayerAttributes().getPlayerPositions().getPrimaryPosition().toString());

                setText(null);
                setGraphic(gridPane);
            }
        }
    }
}
