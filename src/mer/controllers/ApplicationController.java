package mer.controllers;

import javafx.fxml.FXML;

public class ApplicationController {
    @FXML public MenuController menuController;
    @FXML public MembersController membersController;

    @FXML
    public void initialize() {
        System.out.println("Init application");
        menuController.setApplicationController(this);
        membersController.setApplicationController(this);
    }
}
