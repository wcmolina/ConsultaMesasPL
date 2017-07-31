package mer.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ApplicationController {
    //busca por mesa primero, luego selecciona ciudadano
    @FXML public Button searchCitizens;
    //busca por ciudadano primero, luego selecciona mesa
    @FXML public Button searchElectoralTables;
    @FXML public AnchorPane mainContent;
    private Parent tablesRoot;
    private Parent citizensRoot;

    @FXML
    public void initialize() {
        System.out.println("Init application");
        //Default main content: search by electoral tables
        try {
            tablesRoot = FXMLLoader.load(getClass().getResource("/mer/views/SearchElectoralTable.fxml"));
            citizensRoot = FXMLLoader.load(getClass().getResource("/mer/views/SearchCitizen.fxml"));
            mainContent.getChildren().setAll(tablesRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToTableView() {
        mainContent.getChildren().setAll(tablesRoot);
    }

    public void changeToCitizenView() {
        mainContent.getChildren().setAll(citizensRoot);
    }
}
