package mer.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class ElectoralTablesController {
    public TableView results;
    public ComboBox departments;
    public ComboBox filterBy;
    public JFXTextField queryInput;
    public JFXButton search;

    public void initialize() {
        System.out.println("Init tables");
        results.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void performQuery() {
        System.out.println("Buscar mesa!");
    }
}
