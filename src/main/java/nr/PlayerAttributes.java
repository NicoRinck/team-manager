package nr;

import java.time.LocalDate;
import java.util.TreeSet;

public class PlayerAttributes implements Attributes<Player> {

    private PlayerName playerName;
    private LocalDate birthDate;
    private TreeSet<Position> positions;
    private Address address;


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

