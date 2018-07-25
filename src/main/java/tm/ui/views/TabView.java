package tm.ui.views;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import tm.ui.components.Component;

import java.util.ArrayList;

public class TabView implements Component {

    private final TabPane tabPane = new TabPane();
    private final ArrayList<Tab> tabs = new ArrayList<>();

    public TabView() {
        initTabPane();
    }

    private void initTabPane() {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }

    private void addTab(String title, Node content) {
        Tab tab = new Tab();
        tab.setContent(content);
        tab.setText(title);
        tabs.add(tab);
    }

    @Override
    public Node getComponent() {
        tabPane.getTabs().addAll(tabs);
        return tabPane;
    }
}
