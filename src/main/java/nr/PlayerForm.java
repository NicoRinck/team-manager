package nr;

public class PlayerForm implements Form<Player> {

    private final PlayerAttributes playerAttributes;

    public PlayerForm(PlayerAttributes playerAttributes) {
        this.playerAttributes = playerAttributes;
    }
}
