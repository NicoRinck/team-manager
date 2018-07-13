package nr.data_model.entities.player;

import nr.data_model.entities.EntityAttributes;
import nr.data_model.form_fields.BirthDate;
import nr.data_model.form_fields.PlayerName;
import nr.data_model.form_fields.position.PlayerPositions;
import nr.data_model.form_fields.address.Address;

public class PlayerAttributes implements EntityAttributes<Player> {

    private PlayerName playerName;
    private BirthDate birthDate;
    private PlayerPositions playerPositions;
    private Address address;


    public PlayerAttributes(PlayerName playerName, BirthDate birthDate, PlayerPositions playerPositions) {
        this.playerName = playerName;
        this.birthDate = birthDate;
        this.playerPositions = playerPositions;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public PlayerPositions getPositions() {
        return this.playerPositions ;
    }

    public void setPlayerName(PlayerName playerName) {
        this.playerName = playerName;
    }
}

