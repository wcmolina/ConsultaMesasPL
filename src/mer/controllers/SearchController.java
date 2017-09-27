package mer.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mer.dao.MiembroDAO;
import mer.models.MesaElectoral;
import mer.models.Miembro;

public class SearchController {
    public JFXTextField query;
    public JFXComboBox departamentos;
    public JFXComboBox filtrarPor;
    // Execute query
    public JFXButton consultar;
    // Execute query in the table selected in this field (Miembros or Mesas)
    public JFXComboBox buscarEn;
    // Reference to the table displaying the query results
    public TableView resultados;
    public MiembroDAO miembroDAO;

    public void initialize() {
        System.out.println("Init search");
        // Execute query
        query.setOnAction(event -> executeQuery());
        consultar.setOnAction(event -> executeQuery());

        // Change table view columns depending on this value
        buscarEn.setOnAction(event -> changeResultsTable((String) buscarEn.getValue()));
    }

    public void setResultsTable(TableView resultados) {
        this.resultados = resultados;
    }

    public void executeQuery() {
        if (!query.getText().isEmpty()) {
            String selected = (String) buscarEn.getValue();
            if (selected.equals("Miembros MER")) {
                resultados.setItems(miembroDAO.buscarTodos());
                //detallesController.clearTextFields();
            } else if (selected.equals("Mesas electorales")) {
                //resultados.setItems(miembroDao.buscarTodos());
                //detallesController.clearTextFields();
            }
        }
    }

    public void changeResultsTable(String selected) {
        if (selected.equals("Miembros MER")) {
            setResultsToMiembros();
        } else if (selected.equals("Mesas electorales")) {
            setResultsToMesas();
        }
        resultados.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void setResultsToMiembros() {
        resultados.getColumns().clear();
        //Create columns, distribute width and set cell value factories
        for (int i = 0; i < Miembro.TABLE_COLUMNS.length; i++) {
            TableColumn<Miembro, String> column = new TableColumn<>();
            column.setText(Miembro.TABLE_COLUMNS[i]);
            column.setCellValueFactory(new PropertyValueFactory(Miembro.TABLE_PROPERTIES[i]));
            resultados.getColumns().add(column);
        }
        resultados.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //On row selection, show citizen details in the bottom pane, using the citizenDetailsController
        resultados.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                //detallesController.displayCitizenDetails((Miembro) resultado.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void setResultsToMesas() {
        resultados.getColumns().clear();
        //Create columns, distribute width and set cell value factories
        for (int i = 0; i < MesaElectoral.TABLE_COLUMNS.length; i++) {
            TableColumn<MesaElectoral, String> column = new TableColumn<>();
            column.setText(MesaElectoral.TABLE_COLUMNS[i]);
            column.setCellValueFactory(new PropertyValueFactory(MesaElectoral.TABLE_PROPERTIES[i]));
            resultados.getColumns().add(column);
        }
        resultados.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //On row selection, show citizen details in the bottom pane, using the citizenDetailsController
        resultados.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                //detallesController.displayCitizenDetails((Miembro) resultado.getSelectionModel().getSelectedItem());
            }
        });
    }
}
