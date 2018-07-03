package nr.data_model.entities.player;

import nr.data_model.entities.EntityAttributes;
import nr.data_model.form_fields.address.Address;
import nr.data_model.form_fields.PlayerName;
import nr.data_model.form_fields.Position;

import java.time.LocalDate;
import java.util.TreeSet;

public class PlayerAttributes implements EntityAttributes<Player> {

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

    public void setPlayerName(PlayerName playerName) {
        this.playerName = playerName;
    }
}

