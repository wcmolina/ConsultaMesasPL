package mer.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class ApplicationController {
    public AnchorPane mainContent;
    public Button findByTables;
    public Button findByCitizens;
    private Node electoralTablesRootNode;
    private Node citizensRootNode;

    public void initialize() {
        System.out.println("Init application");
        try {
            electoralTablesRootNode = FXMLLoader.load(getClass().getResource("/mer/views/ElectoralTables.fxml"));
            citizensRootNode = FXMLLoader.load(getClass().getResource("/mer/views/Citizens.fxml"));
            //Default main content: search by electoral tables
            changeToTableView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToTableView() {
        findByTables.setStyle("-fx-background-color: lightgrey;");
        findByCitizens.setStyle("-fx-background-color: transparent;");
        mainContent.getChildren().setAll(electoralTablesRootNode);
    }

    public void changeToCitizenView() {
        findByCitizens.setStyle("-fx-background-color: lightgrey;");
        findByTables.setStyle("-fx-background-color: transparent;");
        mainContent.getChildren().setAll(citizensRootNode);
    }
}
