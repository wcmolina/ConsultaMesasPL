package mer.controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ApplicationController {
    public AnchorPane mainContent;
    public Button findByTables;
    public Button findByCitizens;
    private Node electoralTablesRootNode;
    private Node citizensRootNode;

    public void initialize() {
        System.out.println("Init application");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mer/views/Index.fxml"));
            loader.setController(new MesaElectoralController());
            electoralTablesRootNode = loader.load();

            loader = new FXMLLoader(getClass().getResource("/mer/views/Index.fxml"));
            loader.setController(new MiembroController());
            citizensRootNode = loader.load();

            //Default main content: buscar by electoral tables
            changeToTableView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToTableView() {
        findByTables.setStyle("" +
                "-fx-background-color: #BBBBBB;" +
                "-fx-font-weight: bold;");
        findByCitizens.setStyle("" +
                "-fx-background-color: transparent;" +
                "-fx-font-weight: normal;");
        mainContent.getChildren().setAll(electoralTablesRootNode);
    }

    public void changeToCitizenView() {
        findByCitizens.setStyle("" +
                "-fx-background-color: #BBBBBB;" +
                "-fx-font-weight: bold;");
        findByTables.setStyle("" +
                "-fx-background-color: transparent;" +
                "-fx-font-weight: normal;");
        mainContent.getChildren().setAll(citizensRootNode);
    }
}
