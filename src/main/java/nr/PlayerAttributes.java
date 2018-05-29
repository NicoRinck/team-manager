package nr;

import java.util.Date;

public class PlayerAttributes {

    private final PlayerName playerName;
    private final Date birthDate;
    private final Postition[] positions;

    public PlayerAttributes(PlayerName playerName, Date birthDate, Postition[] positions) {
        this.playerName = playerName;
        this.birthDate = birthDate;
        this.positions = positions;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Postition[] getPositions() {
        return positions;
    }
}
