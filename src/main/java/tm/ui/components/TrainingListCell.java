package tm.ui.components;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import tm.data_model.entities.appointment.Training;

public class TrainingListCell extends ListCell<Training> implements ListItemStrategy<Training> {

    private HBox hBox = new HBox();
    private Label dateTime = new Label();
    private Label info = new Label(" Training");

    public TrainingListCell() {
        hBox.setSpacing(5);
        hBox.getChildren().addAll(dateTime, info);
        hBox.setId("h-box");
        info.setId("info-label");
        hBox.getStylesheets().add("/css/AppointmentListItem.css");
    }

    @Override
    public ListCell<Training> getStrategy() {
        return new TrainingListCell();
    }

    @Override
    protected void updateItem(Training item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {

            setText(null);
            setGraphic(null);

        } else {
            dateTime.setText("  " + item.getTrainingAttributes().getDateTime().getDateTimeString());
            setText(null);
            setGraphic(hBox);
        }
    }

}
