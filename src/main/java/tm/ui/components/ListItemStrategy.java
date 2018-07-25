package tm.ui.components;

import javafx.scene.control.ListCell;
import tm.data_model.entities.Entity;

public interface ListItemStrategy<T extends Entity> {

    ListCell<T> getStrategy();
}
