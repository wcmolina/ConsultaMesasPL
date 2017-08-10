package mer.controllers;

import mer.dao.CitizenDataAccess;
import mer.models.Citizen;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class CitizensController {
    public TableView results;
    public TextField queryInput;
    public ComboBox departments;
    public ComboBox filterBy;
    public Button search;
    public AnchorPane details;
    public CitizenDetailsController detailsController;
    private CitizenDataAccess citizenDataAccess = new CitizenDataAccess();

    public void initialize() {
        System.out.println("Init citizens");
        //Set on action on query input and search button
        queryInput.setOnAction(event -> performQuery());
        search.setOnAction(event -> performQuery());

        //Load citizen details and set controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mer/views/CitizenDetails.fxml"));
        try {
            details.getChildren().setAll((Node) loader.load());
            detailsController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create columns, distribute width and set cell value factories
        for (int i = 0; i < Citizen.TABLE_COLUMNS.length; i++) {
            TableColumn<Citizen, String> column = new TableColumn<>();
            column.setText(Citizen.TABLE_COLUMNS[i]);
            column.setCellValueFactory(new PropertyValueFactory(Citizen.TABLE_PROPERTIES[i]));
            results.getColumns().add(column);
        }
        results.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //On row selection, show citizen details in the bottom pane, using the citizenDetailsController
        results.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                detailsController.displayCitizenDetails((Citizen) results.getSelectionModel().getSelectedItem());
            }
        });

    }

    public void performQuery() {
        if (!queryInput.getText().isEmpty()) {
            results.setItems(citizenDataAccess.findAll());
            detailsController.clearTextFields();
        }
    }
}
