package nr.ui.views;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import nr.data_model.entities.player.Player;
import nr.data_model.form_fields.address.Address;
import nr.ui.event_handler.EditEntityHandler;
import nr.ui.event_handler.implementation.DeleteEntityHandler;

import java.util.ArrayList;

public class PlayerDetailView implements EntityDetailView<Player> {

    private final Dialog<Player> dialog = new Dialog<>();
    private final GridPane gridPane = new GridPane();
    private final ArrayList<Label> contentLabels = new ArrayList<>();
    private final EditEntityHandler<Player> editPlayerHandler;
    private final DeleteEntityHandler deleteEntityHandler;
    private Player player;

    public PlayerDetailView(EditEntityHandler<Player> editPlayerHandler, DeleteEntityHandler deleteEntityHandler) {
        this.editPlayerHandler = editPlayerHandler;
        this.deleteEntityHandler = deleteEntityHandler;
        initView();
    }

    private void initView() {
        initGrid();
        VBox vBox = new VBox();
        initVBox(vBox);

        HBox buttonBar = new HBox();
        initButtons(buttonBar);
        vBox.getChildren().addAll(buttonBar,gridPane);
        initDialog();
        dialog.getDialogPane().setContent(vBox);

    }

    private void initDialog() {
        dialog.initStyle(StageStyle.UTILITY);
        Window window = dialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(event -> window.hide());
    }

    private void initVBox(VBox vBox) {
        vBox.setSpacing(20);
        vBox.setPrefWidth(400);
        vBox.setPrefHeight(600);
    }

    private void initGrid() {
        initGridPaneContent();
        ColumnConstraints column1 = new ColumnConstraints(50);
        ColumnConstraints column2 = new ColumnConstraints(50);
        column1.setPercentWidth(40);
        column2.setPercentWidth(60);
        gridPane.getColumnConstraints().addAll(column1,column2);
        gridPane.setVgap(15);
        gridPane.setHgap(20);
        gridPane.setStyle("-fx-font-size: 16; -fx-font-weight: bold");
    }

    private void initButtons(HBox buttonBar) {
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.setSpacing(10);
        Button editButton = new Button("Spieler bearbeiten...");
        Button deleteButton = new Button("Spieler löschen...");
        editButton.setOnMouseClicked(event -> editPlayerHandler.editEntity(this.player));
        buttonBar.getChildren().addAll(editButton,deleteButton);
    }

    private void initGridPaneContent() {
        final String[] labelStrings = {"Vorname: ", "Nachname: ", "Geburtsdatum: ", "Position(en): ", "Adresse: "};
        for (int i = 0; i < labelStrings.length; i++) {
            gridPane.add(new Label("  " + labelStrings[i]), 0,i);
            Label contentLabel = new Label();
            contentLabels.add(contentLabel);
            gridPane.add(contentLabel,1,i);
        }
    }

    @Override
    public void showDetailView(Player player, Node parentNode) {
        fillGrid(player);
        dialog.show();
    }

    private void fillGrid(Player player) {
        this.player = player;
        contentLabels.get(0).setText(player.getPlayerAttributes().getPlayerName().getForename());
        contentLabels.get(1).setText(player.getPlayerAttributes().getPlayerName().getSurname());
        contentLabels.get(2).setText(player.getPlayerAttributes().getBirthDate().getBirthDate().toString());
        contentLabels.get(3).setText(player.getPlayerAttributes().getPlayerPositions().toString());
        contentLabels.get(4).setText(getAddressString(player.getPlayerAttributes().getAddress()));
    }

    private String getAddressString(Address address) {
        if (address == null) {
            return "-";
        }
        String result = "";
        if (address.getPostCode() != null) {
            result = address.getPostCode();
        }
        if (address.getResidence() != null && !address.getResidence().equals("")) {
            result += " " + address.getResidence();
        }
        if (address.getStreet() != null && !address.getStreet().equals("")) {
            result += ", " + address.getStreet();
        }
        if (address.getHouseNumber() > 0) {
            result += " " + address.getHouseNumber();
        }
        return result;
    }
}
