package nr.ui.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import nr.data_model.entities.Entity;
import nr.ui.event_handler.ListItemClickedHandler;

import java.util.List;

public class EntityList<T extends Entity> implements Component {

    private final ListView<T> listView;

    public EntityList(List<T> entityList, ListItemStrategy<T> listItemStrategy, ListItemClickedHandler<T> openDetails) {
        final ObservableList<T> observableList = FXCollections.observableArrayList(entityList);
        listView = new ListView<>(observableList);
        initListCells(listItemStrategy,openDetails);

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




}
