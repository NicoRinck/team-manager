package nr.ui.event_handler;

import nr.data_model.entities.Entity;
import nr.ui.components.EntityList;

public class AddEntityHandler<T extends Entity> implements AddEntityToListHandler<T> {

    @Override
    public void addEntityToList(EntityList<T> entityList) {
        System.out.println("called");
    }
}
