package tm.ui.event_handler;

import tm.data_model.entities.Entity;
import tm.ui.views.EntityDetailView;

public interface EditEntityHandler<T extends Entity> {

    void editEntity(T entity, EntityDetailView<T> detailView);
}
