package nr;

import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nr.data_model.entities.player.PlayerAttributes;
import nr.ui.PlayerNameGrid;

public class PlayerForm extends Dialog<PlayerAttributes> implements Form<PlayerAttributes> {

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
        PlayerNameGrid test = new PlayerNameGrid();
        this.getDialogPane().setContent(test);
    }

    private void initGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
    }


    private String getDialogTitle() {
        if (this.playerAttributes == null) {
            return "Spieler erstellen";
        }
        return playerAttributes.getPlayerName().getNameString();
    }

    @Override
    public PlayerAttributes showForm() {
        return null;
    }


}
