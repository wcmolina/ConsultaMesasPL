package mer.controllers;

import javafx.fxml.FXML;

public class MainController {
    @FXML public MenuController menuController;
    @FXML public MasterTableController tableController;

    @FXML
    public void initialize() {
        System.out.println("Init main");
        menuController.setMainController(this);
        tableController.setMainController(this);
    }
}
