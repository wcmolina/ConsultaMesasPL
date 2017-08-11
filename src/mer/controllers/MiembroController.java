package mer.controllers;

import mer.dao.MiembroDAO;
import mer.models.Miembro;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class MiembroController {
    public TableView resultado;
    public TextField consulta;
    public ComboBox departmentos;
    public ComboBox filtrarPor;
    public Button buscar;
    public AnchorPane detalles;
    public MiembroInfoController detallesController;
    private MiembroDAO miembroDao = new MiembroDAO();

    public void initialize() {
        System.out.println("Init citizens");
        //Set on action on query input and buscar button
        consulta.setOnAction(event -> procesarConsulta());
        buscar.setOnAction(event -> procesarConsulta());

        //Load citizen details and set controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mer/views/MiembroInfo.fxml"));
        try {
            detalles.getChildren().setAll((Node) loader.load());
            detallesController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create columns, distribute width and set cell value factories
        for (int i = 0; i < Miembro.TABLE_COLUMNS.length; i++) {
            TableColumn<Miembro, String> column = new TableColumn<>();
            column.setText(Miembro.TABLE_COLUMNS[i]);
            column.setCellValueFactory(new PropertyValueFactory(Miembro.TABLE_PROPERTIES[i]));
            resultado.getColumns().add(column);
        }
        resultado.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //On row selection, show citizen details in the bottom pane, using the citizenDetailsController
        resultado.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                detallesController.displayCitizenDetails((Miembro) resultado.getSelectionModel().getSelectedItem());
            }
        });

    }

    public void procesarConsulta() {
        if (!consulta.getText().isEmpty()) {
            resultado.setItems(miembroDao.buscarTodos());
            detallesController.clearTextFields();
        }
    }
}
