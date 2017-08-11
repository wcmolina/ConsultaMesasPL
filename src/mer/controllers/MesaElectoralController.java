package mer.controllers;

import mer.dao.MesaElectoralDAO;
import mer.models.MesaElectoral;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MesaElectoralController {
    public TableView resultado;
    public ComboBox departamentos;
    public ComboBox filtrarPor;
    public JFXTextField consulta;
    public JFXButton buscar;
    private MesaElectoralDAO mesaDao = new MesaElectoralDAO();

    public void initialize() {
        System.out.println("Init tables");
        //Set on action on query input and buscar button
        consulta.setOnAction(event -> procesarConsulta());
        buscar.setOnAction(event -> procesarConsulta());

        //Load citizen details and set controller


        //Create columns, distribute width and set cell value factories
        for (int i = 0; i < MesaElectoral.TABLE_COLUMNS.length; i++) {
            TableColumn<MesaElectoral, String> column = new TableColumn<>();
            column.setText(MesaElectoral.TABLE_COLUMNS[i]);
            column.setCellValueFactory(new PropertyValueFactory(MesaElectoral.TABLE_PROPERTIES[i]));
            resultado.getColumns().add(column);
        }
        resultado.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //On row selection, show citizen details in the bottom pane, using the citizenDetailsController
        resultado.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("Show table details (to-do)");
            }
        });

    }

    public void procesarConsulta() {
        if (!consulta.getText().isEmpty()) {
            resultado.setItems(mesaDao.buscarMesasDonde());
        }
    }
}
