package nr;

import nr.data_model.entities.player.PlayerAttributes;

public class PlayerForm implements Form<PlayerAttributes> {

    private PlayerAttributes playerAttributes;

    //edit Player
    public PlayerForm(PlayerAttributes playerAttributes) {
        this.playerAttributes = playerAttributes;
    }

    //create new Player
    public PlayerForm() {
    }

    @Override
    public PlayerAttributes showForm() {
        return null;
    }

    private PlayerAttributes getFormResults() {
        return this.playerAttributes;
    }
}
