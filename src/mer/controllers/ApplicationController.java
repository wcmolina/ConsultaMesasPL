package mer.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class ApplicationController {
    public AnchorPane mainContent;
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
        mainContent.getChildren().setAll(electoralTablesRootNode);
    }

    public void changeToCitizenView() {
        mainContent.getChildren().setAll(citizensRootNode);
    }
}
