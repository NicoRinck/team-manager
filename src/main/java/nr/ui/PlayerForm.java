package nr.ui;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import nr.Form;
import nr.data_converter.user_input_converter.PlayerAttributesInputConverter;
import nr.data_model.entities.player.PlayerAttributes;
import nr.ui.components.form_components.*;

import java.util.Optional;

public class PlayerForm implements Form<PlayerAttributes> {


    private final Dialog<PlayerAttributes> dialog = new Dialog<>();
    private PlayerNameComponent playerNameGrid;
    private BirthDateComponent birthDateComponent;
    private PositionsComponent positionsComponent;
    private AddressComponent addressComponent;

    private final ButtonType okButton = new ButtonType("Speichern", ButtonBar.ButtonData.APPLY);
    private final ButtonType exitButton = new ButtonType("Abbrechen", ButtonBar.ButtonData.CANCEL_CLOSE);
    private PlayerAttributes playerAttributes;

    private boolean active = true;

    public PlayerForm() {
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle(getDialogTitle());
        initButtons();
    }

    private void initButtons() {
        dialog.getDialogPane().getButtonTypes().add(okButton);
        dialog.getDialogPane().getButtonTypes().add(exitButton);
    }

    private void setResultConverter() {
        dialog.setResultConverter((ButtonType) -> {
            if (ButtonType == okButton) {
                PlayerAttributesInputConverter inputConverter = new PlayerAttributesInputConverter();
                playerNameGrid.getFormComponentValue().ifPresent(inputConverter::setPlayerName);
                birthDateComponent.getFormComponentValue().ifPresent(inputConverter::setBirthDate);
                positionsComponent.getFormComponentValue().ifPresent(inputConverter::setPlayerPositions);
                addressComponent.getFormComponentValue().ifPresent(inputConverter::setAddress);
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
        this.initForm(playerNameGrid,birthDateComponent,positionsComponent,addressComponent);
        return showForm();
    }

    @Override
    public Optional<PlayerAttributes> showEditAttributesForm(PlayerAttributes entityAttributes) {
        this.playerAttributes = entityAttributes;
        playerNameGrid = new PlayerNameComponent(playerAttributes.getPlayerName());
        birthDateComponent = new BirthDateComponent(playerAttributes.getBirthDate());
        positionsComponent = new PositionsComponent(playerAttributes.getPlayerPositions());
        addressComponent = new AddressComponent(playerAttributes.getAddress());
        this.initForm(playerNameGrid,birthDateComponent,positionsComponent,addressComponent);
        return showForm();
    }

    private void initForm(FormComponent... formComponents) {
        VBox vBox = new VBox();
        vBox.setPrefWidth(500);
        vBox.setSpacing(20);
        for (FormComponent formComponent : formComponents) {
            vBox.getChildren().add(formComponent.getComponent());
        }
        dialog.getDialogPane().setContent(vBox);
        setResultConverter();
    }

    private Optional<PlayerAttributes> showForm() {
        this.active = true;
        Optional<PlayerAttributes> optional = Optional.empty();
        while (!optional.isPresent() && active) {
            optional = dialog.showAndWait();
        }
        return optional;
    }

}
