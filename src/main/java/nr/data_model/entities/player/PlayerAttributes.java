package nr.data_model.entities.player;

import nr.data_model.entities.EntityAttributes;
import nr.data_model.form_fields.BirthDate;
import nr.data_model.form_fields.PlayerName;
import nr.data_model.form_fields.Position;
import nr.data_model.form_fields.address.Address;

import java.util.TreeSet;

public class PlayerAttributes implements EntityAttributes<Player> {

    private PlayerName playerName;
    private BirthDate birthDate;
    private TreeSet<Position> positions;
    private Address address;


    public PlayerAttributes(PlayerName playerName, BirthDate birthDate, TreeSet<Position> positions) {
        this.playerName = playerName;
        this.birthDate = birthDate;
        this.positions = positions;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public TreeSet<Position> getPositions() {
        return positions;
    }

    public void setPlayerName(PlayerName playerName) {
        this.playerName = playerName;
    }
}

