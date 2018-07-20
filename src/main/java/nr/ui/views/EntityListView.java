package nr.ui.views;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import nr.data_model.entities.Entity;
import nr.ui.components.EntityList;
import nr.ui.event_handler.AddEntityToListHandler;

public class EntityListView<T extends Entity> implements View {

    private VBox vBox = new VBox();
    private final EntityList<T> entityList;
    private final Button addEntityButton;
    private final AddEntityToListHandler<T> addEntityHandler;

    public EntityListView(EntityList<T> entityList, Button addEntityButton, AddEntityToListHandler<T> addEntityHandler) {
        this.entityList = entityList;
        this.addEntityButton = addEntityButton;
        this.addEntityHandler = addEntityHandler;
        initView();
        initChangeListener();
    }

    private void initChangeListener() {
        entityList.getObservableList().addListener((ListChangeListener.Change<? extends T> c) -> {
            EntityList<T> newEntityList = EntityList.createNewEntityList(entityList);
            updateView(newEntityList);
        });
    }
    private void updateView(EntityList<T> entityList) {
        vBox.getChildren().clear();
        vBox.getChildren().addAll(addEntityButton, entityList.getComponent());
    }

    private void initView() {
        initButton();
        vBox.setPrefWidth(400);
        vBox.getChildren().addAll(addEntityButton, entityList.getComponent());
    }

    private void initButton() {
        addEntityButton.setOnMouseClicked(event -> {
            addEntityHandler.addEntityToList(entityList);
            event.consume();
        });
    }

    @Override
    public Node getView() {
        return vBox;
    }
}
