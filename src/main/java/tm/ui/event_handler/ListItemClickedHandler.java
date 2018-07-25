package tm.ui.event_handler;

import javafx.scene.control.ListCell;
import tm.data_model.entities.Entity;

public interface ListItemClickedHandler<T extends Entity> {

    void handleEvent(ListCell<T> entity);

}
