package nr.ui.components.form_components;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nr.ui.components.Component;

import java.util.Optional;

public abstract class FormComponent<T> implements Component {

    protected final int amountOfErrorFields;
    protected Label[] errorLabels;

    public FormComponent(int amountOfErrorFields) {
        this.amountOfErrorFields = amountOfErrorFields;
        this.errorLabels = new Label[amountOfErrorFields];
        initErrorLabels();
    }

    private void initErrorLabels() {
        for (int i = 0; i < this.amountOfErrorFields; i++) {
            Label label = new Label();
            label.setStyle("-fx-text-fill: red");
            errorLabels[i] = label;
        }
    }

    static void markInvalidFields(Control field, Label errorLabel, String errorMessage) {
        field.setStyle("-fx-border-color: red");
        if (!errorMessage.equals("")) {
            errorLabel.setText(errorMessage);
        }
    }

    static void forceNumericInput(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    abstract Optional<T> getFormComponentValue();

    abstract public Node getComponent();

}
