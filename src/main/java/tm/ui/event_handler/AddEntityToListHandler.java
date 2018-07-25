package tm.ui.event_handler;

import tm.data_model.entities.Entity;
import tm.ui.components.EntityList;

public interface AddEntityToListHandler<T extends Entity> {

    void addEntityToList(EntityList<T> entityList);
}
