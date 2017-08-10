package mer.controllers;

import mer.dao.ElectoralTableDataAccess;
import mer.models.ElectoralTable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ElectoralTablesController {
    public TableView results;
    public ComboBox departments;
    public ComboBox filterBy;
    public JFXTextField queryInput;
    public JFXButton search;
    private ElectoralTableDataAccess electoralTableDataAccess = new ElectoralTableDataAccess();

    public void initialize() {
        System.out.println("Init tables");
        //Set on action on query input and search button
        queryInput.setOnAction(event -> performQuery());
        search.setOnAction(event -> performQuery());

        //Load citizen details and set controller


        //Create columns, distribute width and set cell value factories
        for (int i = 0; i < ElectoralTable.TABLE_COLUMNS.length; i++) {
            TableColumn<ElectoralTable, String> column = new TableColumn<>();
            column.setText(ElectoralTable.TABLE_COLUMNS[i]);
            column.setCellValueFactory(new PropertyValueFactory(ElectoralTable.TABLE_PROPERTIES[i]));
            results.getColumns().add(column);
        }
        results.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //On row selection, show citizen details in the bottom pane, using the citizenDetailsController
        results.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("Show table details (to-do)");
            }
        });

    }

    public void performQuery() {
        if (!queryInput.getText().isEmpty()) {
            results.setItems(electoralTableDataAccess.filledTablesWhere());
        }
    }
}
