package nr.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import nr.data_model.form_fields.position.PlayerPositions;
import nr.data_model.form_fields.position.Position;

import java.util.Optional;

public class PositionsComponent extends FormComponent {

    private final HBox hBox = new HBox();

    private final ChoiceBox<Position> primaryPositionField = new ChoiceBox<>();
    private final ChoiceBox<Position> secondaryPositionField = new ChoiceBox<>();
    private final ChoiceBox<Position> tertiaryPositionField = new ChoiceBox<>();

    public PositionsComponent() {
        super(1);
        fillChoiceBoxes();
        initView();
    }

    public PositionsComponent(PlayerPositions playerPositions) {
        super(1);
        fillChoiceBoxes(playerPositions);
        initView();
    }

    private void fillChoiceBoxes() {
        primaryPositionField.setItems(getPositionList(true));

        secondaryPositionField.setItems(getPositionList(true));
        tertiaryPositionField.setItems(getPositionList(true));
        setInitValues();
    }

    private void fillChoiceBoxes(PlayerPositions playerPositions) {
        primaryPositionField.setItems(getPositionListWithoutSelected(false,
                playerPositions.getSecondaryPosition(), playerPositions.getTertiaryPosition()));
        secondaryPositionField.setItems(getPositionListWithoutSelected(true,
                playerPositions.getPrimaryPosition(), playerPositions.getTertiaryPosition()));
        tertiaryPositionField.setItems(getPositionListWithoutSelected(true,
                playerPositions.getPrimaryPosition(), playerPositions.getSecondaryPosition()));
        this.setInitValues(playerPositions);
    }

    private void setInitValues(PlayerPositions playerPositions) {
        primaryPositionField.setValue(playerPositions.getPrimaryPosition());
        secondaryPositionField.setValue(playerPositions.getPrimaryPosition());
        tertiaryPositionField.setValue(playerPositions.getTertiaryPosition());
    }

    private void setInitValues() {
        primaryPositionField.setValue(Position.NO_POSITION);
        secondaryPositionField.setValue(Position.NO_POSITION);
        tertiaryPositionField.setValue(Position.NO_POSITION);
    }

    private void initChoiceBoxes() {
        primaryPositionField.setPrefWidth(70);
        secondaryPositionField.setPrefWidth(70);
        tertiaryPositionField.setPrefWidth(70);

        if (primaryPositionField.getSelectionModel().getSelectedItem() == Position.NO_POSITION) {
            secondaryPositionField.setDisable(true);
        }
        if (secondaryPositionField.getSelectionModel().getSelectedItem() == Position.NO_POSITION) {
            tertiaryPositionField.setDisable(true);
        }
        initChangeListener();
    }

    private void editPositionList(ObservableList<Position> positionList, Position oldValue, Position newValue) {
        if (!positionList.contains(oldValue)) {
            positionList.add(oldValue);
        }
        if (newValue != Position.NO_POSITION) {
            positionList.remove(newValue);
            positionList.remove(null);
        }
    }

    private void initView() {
        initChoiceBoxes();
        hBox.setAlignment(Pos.BASELINE_LEFT);
        hBox.setSpacing(20);
        hBox.getChildren().addAll(new Label("Positionen: "), primaryPositionField,
                secondaryPositionField, tertiaryPositionField, errorLabels[0]);
    }

    @Override
    Optional getComponentValue() {
        return Optional.empty();
    }

    @Override
    public Node getComponent() {
        return hBox;
    }

    private ObservableList<Position> getPositionListWithoutSelected(boolean hasNoPositionElement, Position... selectedPositions) {
        ObservableList<Position> observableList = getPositionList(hasNoPositionElement);
        for (Position selectedPosition : selectedPositions) {
            if (selectedPosition != Position.NO_POSITION) {
                observableList.remove(selectedPosition);
            }
        }
        return observableList;
    }

    private ObservableList<Position> getPositionList(boolean hasNoPositionElement) {
        ObservableList<Position> observableList = FXCollections.observableArrayList(Position.values());
        if (!hasNoPositionElement) {
            observableList.remove(Position.NO_POSITION);
        }
        return observableList;
    }

    private void initChangeListener() {

        primaryPositionField.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Position>() {
            @Override
            public void changed(ObservableValue<? extends Position> observable, Position oldValue, Position newValue) {
                if (oldValue == Position.NO_POSITION) {
                    primaryPositionField.getItems().remove(Position.NO_POSITION);
                }
                secondaryPositionField.setDisable(false);
                editPositionList(secondaryPositionField.getItems(), oldValue, newValue);
                editPositionList(tertiaryPositionField.getItems(), oldValue, newValue);
            }
        });
        secondaryPositionField.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Position>() {
            @Override
            public void changed(ObservableValue<? extends Position> observable, Position oldValue, Position newValue) {
                tertiaryPositionField.setDisable((newValue == Position.NO_POSITION));
                editPositionList(primaryPositionField.getItems(), oldValue, newValue);
                editPositionList(tertiaryPositionField.getItems(), oldValue, newValue);
            }
        });
        tertiaryPositionField.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Position>() {
            @Override
            public void changed(ObservableValue<? extends Position> observable, Position oldValue, Position newValue) {
                editPositionList(primaryPositionField.getItems(), oldValue, newValue);
                editPositionList(secondaryPositionField.getItems(), oldValue, newValue);
            }
        });
    }
}
