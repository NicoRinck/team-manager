package nr.ui.views;

import javafx.scene.Node;
import nr.data_model.entities.Entity;

public interface EntityDetailView<T extends Entity> {

    public void showDetailView(T entity, Node parentNode);
}
