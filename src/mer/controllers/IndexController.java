package mer.controllers;

import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class IndexController {
    public TableView resultados;
    public AnchorPane detalles;
    public MiembroInfoController detallesController;
    public HBox searchBox;
    public SearchController searchBoxController;

    public void initialize() {
        System.out.println("Init index");
        searchBoxController.setResultsTable(resultados);
    }
}
