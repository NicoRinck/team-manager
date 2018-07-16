package nr.ui.components;

import javafx.scene.control.ListCell;
import nr.data_model.entities.Entity;

public interface ListItemStrategy<T extends Entity> {

    ListCell<T> getStrategy();
}
