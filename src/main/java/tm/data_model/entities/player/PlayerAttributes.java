package tm.data_model.entities.player;

import tm.data_model.entities.EntityAttributes;
import tm.data_model.form_fields.BirthDate;
import tm.data_model.form_fields.PlayerName;
import tm.data_model.form_fields.address.Address;
import tm.data_model.form_fields.position.PlayerPositions;

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

    public PlayerPositions getPlayerPositions() {
        return this.playerPositions ;
    }

    public void setPlayerName(PlayerName playerName) {
        this.playerName = playerName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}

