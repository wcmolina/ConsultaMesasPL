package mer.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    public MainController mainController;
    public Stage queryStage;
    public QueryController queryController;

    public void initialize() {
        System.out.println("Init menu");
    }

    public void setMainController(MainController controller) {
        mainController = controller;
    }

    public void showQueryStage() {
        if (queryStage == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/mer/views/Query.fxml"));
                queryStage = new Stage();
                queryStage.setScene(new Scene(loader.load()));
                queryController = loader.getController();
                queryController.setStage(queryStage);
                queryController.setTableController(mainController.tableController);
                queryStage.sizeToScene();
                queryStage.setResizable(false);
                queryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            queryStage.show();
    }
}
