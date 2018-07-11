package nr.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import nr.data_model.form_fields.PlayerName;


public class PlayerNameGrid extends GridPane {

    private PlayerName playerName;
    private final TextField forenameField = new TextField();
    private final TextField surnameField = new TextField();
    private final Label[] errorLabels = {new Label(), new Label()};
    Button btn = new Button("mÖP");

    public PlayerNameGrid(PlayerName playerName) {
        this.playerName = playerName;
        this.fillTextFields();
        this.initGrid();
    }

    public PlayerNameGrid() {
        this.playerName = null;
        this.initGrid();
    }

    private void fillTextFields() {
        this.forenameField.setText(playerName.getForename());
        this.surnameField.setText(playerName.getSurname());
    }

    private void initGrid() {
        this.setHgap(10);
        this.setVgap(10);
        this.setMinWidth(500);

        this.initErrorLabels();
        this.initGridConstraints();
        this.fillGrid();
    }

    private void fillGrid(){
        this.add(new Label("Vorname: "), 0, 0);
        this.add(forenameField, 1, 0);
        this.add(new Label("Nachname: "), 0, 1);
        this.add(surnameField, 1, 1);
        this.add(btn,0,2);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                errorLabels[1].setText("ungültiges Zeichen \n (%,$,*...)");
            }
        });
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
        this.getColumnConstraints().addAll(column1,column2,column3);
    }

    private void initErrorLabels() {
        for (int i = 0; i < 2; i++) {
            errorLabels[i].setStyle("-fx-text-fill: red");
            this.add(errorLabels[i], 2,i);
        }
    }

/*
    public Optional<PlayerAttributes> getValue() {
        boolean validInputs = true;
    }*/
}
