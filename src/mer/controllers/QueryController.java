package mer.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class QueryController {

    public Stage queryStage;
    public TextField queryInput;
    public SplitMenuButton search;
    public MenuItem searchMiembros;
    public MenuItem searchMer;
    public MasterTableController tableController;

    public void initialize() {
        System.out.println("Init query");
        //focus search button
        Platform.runLater( () -> search.requestFocus() );
    }

    public void setTableController(MasterTableController controller) {
        tableController = controller;
    }

    public void setStage(Stage stage) {
        queryStage = stage;
    }

    public void changeSearchBy(ActionEvent event) {
        search.setText(((MenuItem) event.getSource()).getText());
    }

    public void sendQueryToTable() {
        if (!queryInput.getText().isEmpty()) {
            tableController.performQuery(queryInput.getText());
            queryStage.hide();
        }
    }
}
