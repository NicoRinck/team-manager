package nr.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import nr.data_converter.AddressInputConverter;
import nr.data_model.form_fields.address.Address;
import nr.data_model.validator.AddressValidator;
import nr.data_model.validator.NameStringValidator;

import java.util.Optional;

public class AddressComponent extends GridFormComponent<Address> {

    private final TextField residenceField = new TextField();
    private final TextField postCodeField = new TextField();
    private final TextField streetField = new TextField();
    private final TextField houseNumberField = new TextField();

    public AddressComponent() {
        super(4);
        this.init();
    }

    public AddressComponent(Address address) {
        super(4);
        this.fillTextFields(address);
        this.init();
    }

    private void fillTextFields(Address address) {
        if (address != null) {
            residenceField.setText(address.getResidence());
            postCodeField.setText(address.getPostCode());
            streetField.setText(address.getStreet());
            houseNumberField.setText(Integer.toString(address.getHouseNumber()));
        }
    }

    private void init() {
        initTextFields();
        fillGrid();
    }

    private void initTextFields() {
        houseNumberField.setPrefColumnCount(3);
        postCodeField.setMaxWidth(65);
        FormComponent.forceNumericInput(houseNumberField);
        FormComponent.forceNumericInput(postCodeField);
    }

    private void fillGrid() {
        this.gridPane.add(new Label("Wohnort: "), 0, 0);
        this.gridPane.add(residenceField, 1, 0);
        this.gridPane.add(new Label("Postleitzahl: "), 0, 1);
        this.gridPane.add(postCodeField, 1, 1);
        this.gridPane.add(new Label("Straße: "), 0, 2);
        this.gridPane.add(streetField, 1, 2);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_LEFT);
        hBox.getChildren().addAll(new Label("Hausnummer: "), houseNumberField);
        this.gridPane.add(hBox, 2, 2);
    }

    @Override
    protected void addErrorLabels() {
        this.gridPane.add(errorLabels[0], 2, 0);
        this.gridPane.add(errorLabels[1], 2, 1);
        this.gridPane.add(errorLabels[2], 1, 3);
        this.gridPane.add(errorLabels[3], 2, 3);
    }

    @Override
    public Optional<Address> getComponentValue() {
        String residence = residenceField.getText().trim();
        String postCode = postCodeField.getText().trim();
        String street = streetField.getText().trim();
        String houseNumber = houseNumberField.getText().trim();

        AddressInputConverter addressInputConverter = new AddressInputConverter();
        if (!residence.equals("") && !addressInputConverter.setResidence(residence)) {
            markInvalidFields(residenceField, errorLabels[0], NameStringValidator.getErrorMessage(residence));
        }
        if (!postCode.equals("") && !addressInputConverter.setPostCode(postCode)) {
            markInvalidFields(postCodeField, errorLabels[1], AddressValidator.getPostCodeErrorMessage(postCode));
        }
        if (!street.equals("") && !addressInputConverter.setStreet(street)) {
            markInvalidFields(streetField, errorLabels[2], NameStringValidator.getErrorMessage(street));
        }
        if (!houseNumber.equals("") && !addressInputConverter.setHouseNumber(houseNumber)) {
            markInvalidFields(houseNumberField, errorLabels[3], "ungültige Hausnummer");
        }
        return addressInputConverter.convertInputToEntity();
    }
}