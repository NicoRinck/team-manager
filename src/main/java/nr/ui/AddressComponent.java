package nr.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import nr.data_model.form_fields.address.Address;

import java.util.Optional;

public class AddressComponent extends GridFormComponent<Address> {

    private final TextField residenceField = new TextField();
    private final TextField postCodeField = new TextField();
    private final TextField streetField = new TextField();
    private final TextField houseNumberField = new TextField();

    public AddressComponent() {
        super(3);
        this.init();
    }

    public AddressComponent(Address address) {
        super(3);
        this.fillTextFields(address);
        this.init();
    }

    private void init() {
        initTextFields();
        fillGrid();
    }

    private void fillTextFields(Address address) {
        residenceField.setText(address.getResidence());
        postCodeField.setText(address.getPostCode());
        streetField.setText(address.getStreet());
        houseNumberField.setText(Integer.toString(address.getHouseNumber()));
    }

    private void initTextFields() {
        houseNumberField.setPrefColumnCount(3);
        postCodeField.setMaxWidth(65);
        FormComponent.forceNumericInput(houseNumberField);
        FormComponent.forceNumericInput(postCodeField);
    }

    private void fillGrid() {
        this.gridPane.add(new Label("Wohnort: "),0,0);
        this.gridPane.add(residenceField,1,0);
        this.gridPane.add(new Label("Postleitzahl: "),0,1);
        this.gridPane.add(postCodeField,1,1);
        this.gridPane.add(new Label("Straße: "),0,2);
        this.gridPane.add(streetField,1,2);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_LEFT);
        hBox.getChildren().addAll(new Label("Hausnummer: "), houseNumberField);
        this.gridPane.add(hBox,2,2);
    }

    @Override
    protected void initGridConstraints() {
        ColumnConstraints column1 = new ColumnConstraints(90);
        column1.setHgrow(Priority.NEVER);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setFillWidth(false);
        column2.setHgrow(Priority.ALWAYS);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(40);
        column3.setHgrow(Priority.SOMETIMES);
        this.gridPane.getColumnConstraints().addAll(column1,column2,column3);

    }

    @Override
    protected void addErrorLabels() {
       this.gridPane.add(errorLabels[0],2,0);
       this.gridPane.add(errorLabels[1],2,1);
    }

    @Override
    Optional<Address> getComponentValue() {
        return Optional.empty();
    }
}
