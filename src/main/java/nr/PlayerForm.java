package nr;

import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import nr.data_model.entities.player.PlayerAttributes;
import nr.ui.BirthDateComponent;
import nr.ui.PlayerNameComponent;

public class PlayerForm extends Dialog<PlayerAttributes>{

    private PlayerAttributes playerAttributes;

    //edit Player
    public PlayerForm(PlayerAttributes playerAttributes) {
        this.playerAttributes = playerAttributes;
        initForm();
    }

    //create new Player
    public PlayerForm() {
        initForm();
    }

    private void initForm() {
        this.setTitle(getDialogTitle());
        VBox vBox = new VBox();
        vBox.setPrefWidth(600);
        vBox.setSpacing(10);
        PlayerNameComponent playerNameGrid = new PlayerNameComponent();
        BirthDateComponent birthDateComponent = new BirthDateComponent();
        vBox.getChildren().addAll(playerNameGrid.getComponent(),birthDateComponent.getComponent());

        this.getDialogPane().setContent(vBox);
    }

    private String getDialogTitle() {
        if (this.playerAttributes == null) {
            return "Spieler erstellen";
        }
        return playerAttributes.getPlayerName().getNameString();
    }
}
