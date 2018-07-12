package nr.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import nr.data_model.form_fields.address.Address;

import java.util.Optional;

public class AddressComponent extends GridFormComponent<Address> {

    private final TextField residenceField = new TextField();
    private final TextField postCodeField = new TextField();
    private final TextField streetField = new TextField();
    private final TextField houseNumber = new TextField();

    public AddressComponent() {
        super(4);
        this.initTextFields();
    }

    private void initTextFields() {
        houseNumber.setPrefColumnCount(3);
        forceNumericInput(houseNumber);
        forceNumericInput(postCodeField);
    }

    private void forceNumericInput(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }


    @Override
    Optional<Address> getComponentValue() {
        return Optional.empty();
    }
}
