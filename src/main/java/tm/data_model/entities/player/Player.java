package tm.data_model.entities.player;

import tm.data_model.entities.Entity;

public class Player implements Entity {

    private PlayerAttributes playerAttributes;

    public Player(PlayerAttributes playerAttributes) {
        this.playerAttributes = playerAttributes;
    }

    public PlayerAttributes getPlayerAttributes() {
        return playerAttributes;
    }

    public void setPlayerAttributes(PlayerAttributes playerAttributes) {
        this.playerAttributes = playerAttributes;
    }
}
