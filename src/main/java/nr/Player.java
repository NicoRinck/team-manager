package nr;

public class Player implements Entity {

    private final PlayerAttributes playerAttributes;

    public Player(PlayerAttributes playerAttributes) {
        this.playerAttributes = playerAttributes;
    }

    public PlayerAttributes getPlayerAttributes() {
        return playerAttributes;
    }
}
