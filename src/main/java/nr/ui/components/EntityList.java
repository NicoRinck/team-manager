package nr.ui.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import nr.data_model.entities.Entity;

import java.util.List;

public class EntityList<T extends Entity> implements Component {

    private final ListView<T> listView;

    public EntityList(List<T> entityList, ListItemStrategy<T> listItemStrategy) {
        final ObservableList<T> observableList = FXCollections.observableArrayList(entityList);
        listView = new ListView<>(observableList);
        listView.setCellFactory(ListView -> listItemStrategy.getStrategy());
    }

    @Override
    public ListView<T> getComponent() {
        return listView;
    }


}
