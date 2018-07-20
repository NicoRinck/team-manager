package nr.ui.views;

import javafx.scene.Node;
import javafx.scene.control.PopupControl;
import nr.data_model.entities.Entity;
import nr.ui.event_handler.DeleteEntityHandler;
import nr.ui.event_handler.EditEntityHandler;

public class PlayerDetailView implements EntityDetailView {

    private final PopupControl popupControl = new PopupControl();
    private final EditEntityHandler editEntityHandler;
    private final DeleteEntityHandler deleteEntityHandler;

    public PlayerDetailView(EditEntityHandler editEntityHandler, DeleteEntityHandler deleteEntityHandler) {
        this.editEntityHandler = editEntityHandler;
        this.deleteEntityHandler = deleteEntityHandler;
    }

    @Override
    public void showDetailView(Node parentNode, Entity entity) {

    }
}
