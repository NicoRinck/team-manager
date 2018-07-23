package nr.ui.views;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nr.data_model.entities.Entity;
import nr.ui.components.Component;
import nr.ui.components.EntityList;
import nr.ui.event_handler.AddEntityToListHandler;

public class EntityListView<T extends Entity> implements Component {

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

    private void initView() {
        initButton();

        vBox.setPrefWidth(450);
        vBox.setMinHeight(400);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(addEntityButton, getMainContent(entityList));
    }

    private Node getMainContent(EntityList<T> entityList) {
        if (entityList.getObservableList().size() == 0) {
            Label label = new Label("keine Spieler in der Datenbank!");
            label.setStyle("-fx-font-size: 18");
            label.setAlignment(Pos.CENTER);
            return label;
        }
        return entityList.getComponent();
    }

    private void initButton() {
        addEntityButton.setPadding(new Insets( 5));
        addEntityButton.setStyle("-fx-start-margin: 10px");
        addEntityButton.setOnMouseClicked(event -> {
            addEntityHandler.addEntityToList(entityList);
            event.consume();
        });
    }

    @Override
    public Node getComponent() {
        return vBox;
    }

    private void initChangeListener() {
        entityList.getObservableList().addListener((ListChangeListener.Change<? extends T> c) -> {
            EntityList<T> newEntityList = EntityList.createNewEntityList(entityList);
            updateView(newEntityList);
        });
    }
    private void updateView(EntityList<T> entityList) {
        vBox.getChildren().clear();
        vBox.getChildren().addAll(addEntityButton, getMainContent(entityList));
    }
}
