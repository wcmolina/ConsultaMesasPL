package mer.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import mer.App;
import mer.events.QueryEvent;

public class SearchController {
    @FXML
    private JFXTextField query;
    @FXML
    private JFXComboBox<String> departments;
    @FXML
    private JFXComboBox<String> filterBy;
    @FXML
    private JFXButton search;
    @FXML
    private JFXComboBox searchIn;

    public void initialize() {
        System.out.println("Init search");
        // Send query for its execution
        query.setOnAction(event -> sendQuery());
        search.setOnAction(event -> sendQuery());
        departments.getSelectionModel().select("Francisco Morazán");
        searchIn.getSelectionModel().selectFirst();
        searchIn.setOnAction(event -> App.EVENT_BUS.post(searchIn.getValue()));
    }

    private void sendQuery() {
        String context = (String) searchIn.getValue();
        QueryEvent queryEvent = new QueryEvent();
        queryEvent.setQuery(query.getText());
        queryEvent.setContext(context);
        App.EVENT_BUS.post(queryEvent);
    }
}
