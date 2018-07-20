package nr.ui.event_handler;

import javafx.scene.control.ListCell;
import nr.data_model.entities.Entity;

public interface ListItemClickedHandler<T extends Entity> {

    void handleEvent(ListCell<T> entity);

}
