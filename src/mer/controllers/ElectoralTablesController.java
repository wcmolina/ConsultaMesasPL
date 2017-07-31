package mer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class ElectoralTablesController {
    @FXML public TableView electoralTables;

    public void initialize() {
        System.out.println("Init tables");
        electoralTables.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
