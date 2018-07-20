package nr.ui.components;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import nr.data_model.entities.Entity;
import nr.ui.event_handler.ListItemClickedHandler;

public class EntityList<T extends Entity> implements Component {

    private ListView<T> listView;
    private final ObservableList<T> observableList;
    private final ListItemStrategy<T> listItemStrategy;
    private final ListItemClickedHandler<T> openDetails;

    public EntityList(ObservableList<T> observableList, ListItemStrategy<T> listItemStrategy, ListItemClickedHandler<T> openDetails) {
        listView = new ListView<>(observableList);
        this.observableList = observableList;
        this.listItemStrategy = listItemStrategy;
        this.openDetails = openDetails;
        initListCells(listItemStrategy, openDetails);
    }

    private void initListCells(ListItemStrategy<T> listItemStrategy, ListItemClickedHandler<T> onListItemClicked) {
        listView.setCellFactory(ListView -> {
            ListCell<T> cell = listItemStrategy.getStrategy();
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                if (event.getClickCount() > 1 && onListItemClicked != null) {
                    onListItemClicked.handleEvent(cell);
                }
            });
            return cell;
        });
    }

    @Override
    public ListView<T> getComponent() {
        return listView;
    }

    public ObservableList<T> getObservableList() {
        return observableList;
    }

    public ListItemStrategy<T> getListItemStrategy() {
        return listItemStrategy;
    }

    public ListItemClickedHandler<T> getOpenDetails() {
        return openDetails;
    }

    public static <T extends Entity> EntityList<T> createNewEntityList(EntityList<T> entityList) {
        return new EntityList<>(entityList.getObservableList(),entityList.getListItemStrategy(),entityList.getOpenDetails());
    }
}
