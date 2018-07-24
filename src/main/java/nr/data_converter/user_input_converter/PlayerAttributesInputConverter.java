package nr.data_converter.user_input_converter;

import nr.data_model.entities.player.PlayerAttributes;
import nr.data_model.form_fields.BirthDate;
import nr.data_model.form_fields.PlayerName;
import nr.data_model.form_fields.address.Address;
import nr.data_model.form_fields.position.PlayerPositions;

import java.util.Optional;

public class PlayerAttributesInputConverter implements UserInputConverter<PlayerAttributes> {

    private PlayerName playerName;
    private BirthDate birthDate;
    private PlayerPositions playerPositions;
    private Address address;

    @Override
    public Optional<PlayerAttributes> convertInputToEntity() {
        if (playerName != null && birthDate != null && playerPositions != null) {
            PlayerAttributes playerAttributes = new PlayerAttributes(playerName, birthDate, playerPositions);
            if (address != null) {
                playerAttributes.setAddress(address);
            }
            return Optional.of(playerAttributes);
        }
        return Optional.empty();
    }

    public void setPlayerName(PlayerName playerName) {
        this.playerName = playerName;
    }

    public void setBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPlayerPositions(PlayerPositions playerPositions) {
        this.playerPositions = playerPositions;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
