package mer.controllers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.controlsfx.control.MasterDetailPane;

public class ElectoralTablesController {
    public TableView electoralTables;
    public MasterDetailPane masterDetail;
    public TextField electoralTableQuery;
    public ComboBox electoralTableDepartment;
    public ComboBox filterElectoralTablesBy;

    public void initialize() {
        System.out.println("Init tables");
        electoralTables.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
