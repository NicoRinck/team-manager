package nr;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import nr.data_model.Player;

import java.io.IOException;

public class PlayerListCell extends ListCell<Player> {

    FXMLLoader loader = null;
    @FXML private Label playerName;
    @FXML private Label position;
    @FXML private Label test1;
    @FXML private Label test2;
    @FXML private Label birthDate;
    @FXML private GridPane gridPane;


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

                if (position !=null) {
                    /*position.setText(item.getPlayerAttributes().getPositions()[0].toString());*/
                    playerName.setText(item.getPlayerAttributes().getPlayerName().getNameString());
                    test1.setText("test1");
                    test2.setText("test2");
                    birthDate.setText("Date");
                }

                setText(null);
                setGraphic(gridPane);
            }
        }
    }
}
