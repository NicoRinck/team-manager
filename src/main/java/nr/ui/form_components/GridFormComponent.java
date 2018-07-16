package nr.ui.form_components;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import nr.ui.form_components.FormComponent;

import java.util.Optional;

public abstract class GridFormComponent<T> extends FormComponent<T> {

    protected final GridPane gridPane = new GridPane();

    public GridFormComponent(int amountOfFields) {
        super(amountOfFields);
        initGrid();
    }

    private void initGrid() {
        this.gridPane.setHgap(10);
        this.gridPane.setVgap(10);
        this.gridPane.setMinWidth(500);

        initGridConstraints();
        addErrorLabels();
    }

    protected void addErrorLabels() {
        for (int i = 0; i < errorLabels.length; i++) {
            this.gridPane.add(errorLabels[i], 2,i);
        }
    }

    protected void initGridConstraints() {
        ColumnConstraints column1 = new ColumnConstraints(90);
        column1.setHgrow(Priority.NEVER);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setFillWidth(false);
        column2.setHgrow(Priority.ALWAYS);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(40);
        column3.setHgrow(Priority.SOMETIMES);
        this.gridPane.getColumnConstraints().addAll(column1,column2,column3);
    }

    @Override
    public Node getComponent() {
        return this.gridPane;
    }

    abstract Optional<T> getComponentValue();

}
