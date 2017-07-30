package mer.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MesasController {
    @FXML
    public TableView mesas;

    public void initialize() {
        System.out.println("Init mesas");
        final String[] MER_PROPERTIES = {"municipio", "lugar", "centro", "numero"};
        ObservableList<TableColumn> columns = mesas.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).prefWidthProperty().bind(mesas.widthProperty().multiply(0.25));
        }
    }
}
