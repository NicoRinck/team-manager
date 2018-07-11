package nr.ui;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import nr.data_model.form_fields.PlayerName;
import nr.data_model.validator.NameStringValidator;

import java.util.Optional;


public class PlayerNameComponent extends FormComponent<PlayerName> {

    private PlayerName playerName;

    private final GridPane gridPane = new GridPane();
    private final TextField forenameField = new TextField();
    private final TextField surnameField = new TextField();

    public PlayerNameComponent(PlayerName playerName) {
        super(2);
        this.playerName = playerName;
        this.fillTextFields();
        this.initGrid();
    }

    public PlayerNameComponent() {
        super(2);
        this.playerName = null;
        this.initGrid();
    }

    private void fillTextFields() {
        this.forenameField.setText(playerName.getForename());
        this.surnameField.setText(playerName.getSurname());
    }

    private void initGrid() {
        this.gridPane.setHgap(10);
        this.gridPane.setVgap(10);
        this.gridPane.setMinWidth(500);

        this.installEventHandler(forenameField, surnameField);
        this.addErrorLabels();
        this.initGridConstraints();
        this.fillGrid();
    }

    private void fillGrid(){
        this.gridPane.add(new Label("Vorname: "), 0, 0);
        this.gridPane.add(forenameField, 1, 0);
        this.gridPane.add(new Label("Nachname: "), 0, 1);
        this.gridPane.add(surnameField, 1, 1);
    }

    private void initGridConstraints() {
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setMinWidth(80);
        column1.setHgrow(Priority.NEVER);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(55);
        column2.setHgrow(Priority.ALWAYS);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(40);
        column3.setHgrow(Priority.SOMETIMES);
        this.gridPane.getColumnConstraints().addAll(column1,column2,column3);
    }

    private void addErrorLabels() {
        for (int i = 0; i < errorLabels.length; i++) {
            this.gridPane.add(errorLabels[i], 2,i);
        }
    }

    @Override
    public Node getComponent() {
        return gridPane;
    }

    @Override
    public Optional<PlayerName> getComponentValue() {
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
