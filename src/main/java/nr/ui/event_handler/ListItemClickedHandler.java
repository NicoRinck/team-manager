package nr.ui.event_handler;

import javafx.scene.control.ListCell;

public interface ListItemClickedHandler<T> {

    void handleEvent(ListCell<T> entity);

}
