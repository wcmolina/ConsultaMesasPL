package mer.controllers;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import mer.dao.CitizenDataAccess;
import mer.models.Citizen;
import org.controlsfx.control.MasterDetailPane;

public class CitizensController {
    public MasterDetailPane masterDetail;
    public TableView citizens;
    public TextField citizenQuery;
    public VBox citizenDetails;
    public ComboBox filterCitizensBy;
    public Button searchCitizen;
    public CitizenDetailsController citizenDetailsController;
    private CitizenDataAccess citizenDataAccess = new CitizenDataAccess();

    public void initialize() {
        System.out.println("Init citizens");
        //Distribute column width and set cell value factories
        ObservableList<TableColumn> columns = citizens.getColumns();
        citizens.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).setCellValueFactory(new PropertyValueFactory(Citizen.TABLE_COLUMNS[i]));
        }

        citizens.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                citizenDetailsController.displayCitizenDetails((Citizen) citizens.getSelectionModel().getSelectedItem());
            }
        });

    }

    public void performQuery() {
        if (!citizenQuery.getText().isEmpty()) {
            citizens.setItems(citizenDataAccess.findAll(citizenQuery.getText()));
        }
    }
}
