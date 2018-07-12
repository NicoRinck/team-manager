package nr.ui;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import nr.data_model.form_fields.address.Address;

import java.util.Optional;

public class AddressComponent extends FormComponent<Address> {

    private final GridPane gridPane = new GridPane();


    public AddressComponent() {
        super(4);
        this.initGrid();
    }

    private void initGrid() {
    }


    @Override
    Optional<Address> getComponentValue() {
        return Optional.empty();
    }

    @Override
    Node getComponent() {
        return null;
    }
}
