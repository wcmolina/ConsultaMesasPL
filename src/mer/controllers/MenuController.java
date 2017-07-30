package mer.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MenuController {

    public ApplicationController applicationController;
    public Stage queryStage;
    public QueryController queryController;

    public void initialize() {
        System.out.println("Init menu");
    }

    public void setApplicationController(ApplicationController controller) {
        applicationController = controller;
    }

    public void showQueryStage() {
        if (queryStage == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/mer/views/Query.fxml"));
                queryStage = new Stage();
                queryStage.setScene(new Scene(loader.load()));
                queryController = loader.getController();
                queryController.setStage(queryStage);
                queryController.setTableController(applicationController.membersController);
                queryStage.sizeToScene();
                queryStage.setResizable(false);
                queryStage.initModality(Modality.APPLICATION_MODAL);
                queryStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            queryStage.show();
    }
}
