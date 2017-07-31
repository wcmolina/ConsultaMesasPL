package mer.controllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mer.dao.CitizenDataAccess;
import mer.models.Citizen;

public class CitizensController {
    private CitizenDataAccess citizenDataAccess = new CitizenDataAccess();
    @FXML public TableView citizens;
    @FXML public TextField citizenQuery;

    @FXML
    public void initialize() {
        System.out.println("Init citizens");
        ObservableList<TableColumn> columns = citizens.getColumns();
        citizens.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).setCellValueFactory(new PropertyValueFactory(Citizen.TABLE_COLUMNS[i]));
        }
    }

    public void performQuery() {
        if (!citizenQuery.getText().isEmpty()) {
            citizens.setItems(citizenDataAccess.findAll(citizenQuery.getText()));
        }
    }
}
