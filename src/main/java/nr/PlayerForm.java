package nr;

import nr.data_model.entities.player.Player;
import nr.data_model.entities.player.PlayerAttributes;

public class PlayerForm implements Form<Player> {

    private PlayerAttributes playerAttributes;

    //edit Player
    public PlayerForm(PlayerAttributes playerAttributes) {
        this.playerAttributes = playerAttributes;
    }

    //create new Player
    public PlayerForm() {
    }

    private void showForm() {

    }

    private PlayerAttributes getFormResults() {
        return this.playerAttributes;
    }
}
