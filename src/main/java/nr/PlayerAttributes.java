package nr;

import java.time.LocalDate;
import java.util.TreeSet;

public class PlayerAttributes implements Attributes<Player> {

    private final PlayerName playerName;
    private final LocalDate birthDate;
    private final TreeSet<Position> positions;

    public PlayerAttributes(PlayerName playerName, LocalDate birthDate, TreeSet<Position> positions) {
        this.playerName = playerName;
        this.birthDate = birthDate;
        this.positions = positions;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public TreeSet<Position> getPositions() {
        return positions;
    }
}

