package nr.ui.views;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import nr.data_model.entities.Entity;
import nr.ui.components.EntityList;
import nr.ui.event_handler.AddEntityHandler;

public class EntityListView<T extends Entity> implements View {

    private final VBox vBox = new VBox();
    private final EntityList<T> entityList;
    private final Button addEntityButton;
    private final AddEntityHandler<T> addEntityHandler;

    public EntityListView(EntityList<T> entityList, Button addEntityButton, AddEntityHandler<T> addEntityHandler) {
        this.entityList = entityList;
        this.addEntityButton = addEntityButton;
        this.addEntityHandler = addEntityHandler;
        initView();
    }

    private void initView() {
        initButton();
        vBox.setPrefWidth(400);
        ListView<T> listView = entityList.getComponent();
        vBox.getChildren().addAll(addEntityButton,listView);
    }

    private void initButton() {
        addEntityButton.setOnMouseClicked( event ->  {
            addEntityHandler.addEntityToList(entityList);
            event.consume();
        });
    }

    @Override
    public Node getView() {
        return vBox;
    }
}
