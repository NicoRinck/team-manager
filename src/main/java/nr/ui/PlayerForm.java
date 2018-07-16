package nr.ui;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import nr.Form;
import nr.data_converter.PlayerAttributesInputConverter;
import nr.data_model.entities.player.PlayerAttributes;
import nr.ui.form_components.AddressComponent;
import nr.ui.form_components.BirthDateComponent;
import nr.ui.form_components.PlayerNameComponent;
import nr.ui.form_components.PositionsComponent;

import java.util.Optional;

public class PlayerForm implements Form<PlayerAttributes> {


    private final Dialog<PlayerAttributes> dialog = new Dialog<>();
    private final PlayerNameComponent playerNameGrid;
    private final BirthDateComponent birthDateComponent;
    private final PositionsComponent positionsComponent;
    private final AddressComponent addressComponent;

    private final ButtonType okButton = new ButtonType("Speichern", ButtonBar.ButtonData.APPLY);
    private final ButtonType exitButton = new ButtonType("Abbrechen", ButtonBar.ButtonData.CANCEL_CLOSE);
    private PlayerAttributes playerAttributes;

    private boolean active = true;

    public PlayerForm(PlayerAttributes playerAttributes) {
        this.playerAttributes = playerAttributes;
        playerNameGrid = new PlayerNameComponent(playerAttributes.getPlayerName());
        birthDateComponent = new BirthDateComponent(playerAttributes.getBirthDate());
        positionsComponent = new PositionsComponent(playerAttributes.getPlayerPositions());
        addressComponent = new AddressComponent(playerAttributes.getAddress());
        initForm();
    }

    public PlayerForm() {
        playerNameGrid = new PlayerNameComponent();
        birthDateComponent = new BirthDateComponent();
        positionsComponent = new PositionsComponent();
        addressComponent = new AddressComponent();
        initForm();
    }



    private void initForm() {
        dialog.setTitle(getDialogTitle());
        VBox vBox = new VBox();
        vBox.setPrefWidth(500);
        vBox.setSpacing(20);
        vBox.getChildren().addAll(playerNameGrid.getComponent(), birthDateComponent.getComponent(),
                positionsComponent.getComponent(), addressComponent.getComponent());
        dialog.getDialogPane().setContent(vBox);
        this.initButtons();
    }

    private void initButtons() {

        dialog.getDialogPane().getButtonTypes().add(okButton);
        dialog.getDialogPane().getButtonTypes().add(exitButton);
        setResultConverter();
    }

    private void setResultConverter() {
        dialog.setResultConverter((ButtonType) -> {
            if (ButtonType == okButton) {
                PlayerAttributesInputConverter inputConverter = new PlayerAttributesInputConverter();
                playerNameGrid.getComponentValue().ifPresent(inputConverter::setPlayerName);
                birthDateComponent.getComponentValue().ifPresent(inputConverter::setBirthDate);
                positionsComponent.getComponentValue().ifPresent(inputConverter::setPlayerPositions);
                addressComponent.getComponentValue().ifPresent(inputConverter::setAddress);
                if (inputConverter.convertInputToEntity().isPresent()) {
                    return inputConverter.convertInputToEntity().get();
                }
            }
            this.active = false;
            return null;
        });
    }

    private String getDialogTitle() {
        if (this.playerAttributes == null) {
            return "Spieler erstellen";
        }
        return playerAttributes.getPlayerName().getNameString();
    }

    @Override
    public Optional<PlayerAttributes> showForm() {
        this.active = true;
        Optional<PlayerAttributes> optional = Optional.empty();
        while (!optional.isPresent() && active) {
            optional = dialog.showAndWait();
        }
        return optional;
    }
}
