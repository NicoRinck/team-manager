package nr.ui.components.form_components;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nr.data_model.form_fields.PlayerName;
import nr.data_model.validator.NameStringValidator;

import java.util.Optional;


public class PlayerNameComponent extends GridFormComponent<PlayerName> {

    private final TextField forenameField = new TextField();
    private final TextField surnameField = new TextField();

    public PlayerNameComponent(PlayerName playerName) {
        super(2);
        this.fillTextFields(playerName);
        this.init();
    }

    public PlayerNameComponent() {
        super(2);
        this.init();
    }

    private void fillTextFields(PlayerName playerName) {
        this.forenameField.setText(playerName.getForename());
        this.surnameField.setText(playerName.getSurname());
    }

    private void init() {
        this.fillGrid();
    }

    private void fillGrid(){
        this.gridPane.add(new Label("Vorname: "), 0, 0);
        this.gridPane.add(forenameField, 1, 0);
        this.gridPane.add(new Label("Nachname: "), 0, 1);
        this.gridPane.add(surnameField, 1, 1);
    }

    @Override
    public Optional<PlayerName> getFormComponentValue() {
        String forename = forenameField.getText().trim();
        String surname = surnameField.getText().trim();

        if (NameStringValidator.isValidNameString(forename) && NameStringValidator.isValidNameString(surname)) {
            return Optional.of(new PlayerName(forename,surname));
        }
        markInvalidFields(forenameField, errorLabels[0], NameStringValidator.getErrorMessage(forename));
        markInvalidFields(surnameField,errorLabels[1], NameStringValidator.getErrorMessage(surname));
        return Optional.empty();
    }


}
