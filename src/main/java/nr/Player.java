package nr;

public class Player implements Entity {

    private final Attributes<Player> playerAttributes;

    public Player(PlayerAttributes playerAttributes) {
        this.playerAttributes = playerAttributes;
    }

    public Attributes<Player> getPlayerAttributes() {
        return playerAttributes;
    }
}
