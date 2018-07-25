package tm.ui.forms;

import tm.data_converter.user_input_converter.PlayerAttributesInputConverter;
import tm.data_model.entities.player.PlayerAttributes;
import tm.ui.components.form_components.*;

import java.util.Optional;

public class PlayerForm extends DialogForm<PlayerAttributes> {

    private PlayerNameComponent playerNameGrid;
    private BirthDateComponent birthDateComponent;
    private PositionsComponent positionsComponent;
    private AddressComponent addressComponent;

    private PlayerAttributes playerAttributes;

    public PlayerForm() {}

    @Override
    Optional<PlayerAttributes> getConvertedInput() {
        final PlayerAttributesInputConverter inputConverter = new PlayerAttributesInputConverter();
        playerNameGrid.getFormComponentValue().ifPresent(inputConverter::setPlayerName);
        birthDateComponent.getFormComponentValue().ifPresent(inputConverter::setBirthDate);
        positionsComponent.getFormComponentValue().ifPresent(inputConverter::setPlayerPositions);
        addressComponent.getFormComponentValue().ifPresent(inputConverter::setAddress);
        return inputConverter.convertInputToEntity();
    }

    private String getDialogTitle() {
        if (this.playerAttributes == null) {
            return "  Spieler erstellen";
        }
        return "  " + playerAttributes.getPlayerName().getNameString();
    }

    @Override
    public Optional<PlayerAttributes> showCreateAttributesForm() {
        playerNameGrid = new PlayerNameComponent();
        birthDateComponent = new BirthDateComponent();
        positionsComponent = new PositionsComponent();
        addressComponent = new AddressComponent();
        this.initForm(playerNameGrid, birthDateComponent, positionsComponent, addressComponent);
        return showForm();
    }

    @Override
    public Optional<PlayerAttributes> showEditAttributesForm(PlayerAttributes entityAttributes) {
        this.playerAttributes = entityAttributes;
        playerNameGrid = new PlayerNameComponent(playerAttributes.getPlayerName());
        birthDateComponent = new BirthDateComponent(playerAttributes.getBirthDate());
        positionsComponent = new PositionsComponent(playerAttributes.getPlayerPositions());
        addressComponent = new AddressComponent(playerAttributes.getAddress());
        this.initForm(playerNameGrid, birthDateComponent, positionsComponent, addressComponent);
        return showForm();
    }

    private void initForm(FormComponent... formComponents) {
        initDialog();
        dialog.setTitle(getDialogTitle());
        vBox.getChildren().clear();
        for (FormComponent formComponent : formComponents) {
            vBox.getChildren().add(formComponent.getComponent());
        }
    }
}
