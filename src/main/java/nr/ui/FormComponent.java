package nr.ui;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;

import java.util.Optional;

public abstract class FormComponent<T> {

    protected final int amountOfFields;
    protected Label[] errorLabels;

    public FormComponent(int amountOfFields) {
        this.amountOfFields = amountOfFields;
        this.errorLabels = new Label[amountOfFields];
        initErrorLabels();
    }

    private void initErrorLabels() {
        for (int i = 0; i < this.amountOfFields; i++) {
            Label label = new Label();
            label.setStyle("-fx-text-fill: red");
            errorLabels[i] = label;
        }
    }

    protected void installEventHandler(Control... controls) {
        for (final Control control: controls) {
            control.setOnMouseClicked(e -> {
                control.setStyle("-fx-border-color: red");
            });
            control.setOnMouseExited(e -> {
                control.setStyle("-fx-border-color: gray");
            });
        }
    }

    protected void markInvalidFields(Control field, Label errorLabel, String errorMessage) {
        field.setStyle("-fx-border-color: red");
        if (!errorMessage.equals("")) {
            errorLabel.setText(errorMessage);
        }
    }

    abstract Optional<T> getComponentValue();

    abstract Node getComponent();

}
