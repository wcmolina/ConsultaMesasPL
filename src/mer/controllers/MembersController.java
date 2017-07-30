package mer.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mer.models.MiembroMesaElectoral;
import mer.dao.MiembroMerDataAccess;

public class MembersController {
    @FXML private TableView miembros;
    private MiembroMerDataAccess miembroData;
    public ApplicationController applicationController;


    @FXML
    public void initialize() {
        System.out.println("Init members");
        miembroData = new MiembroMerDataAccess();
        //must be in order of column appearance
        final String[] MIEMBRO_MER_PROPERTIES = {"primerNombre", "primerApellido", "numeroIdentidad", "direccion", "numeroMesa"};
        ObservableList<TableColumn> columns = miembros.getColumns();

        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).prefWidthProperty().bind(miembros.widthProperty().multiply(0.20));
            columns.get(i).setCellValueFactory(new PropertyValueFactory(MIEMBRO_MER_PROPERTIES[i]));
        }

        //double-click event to each row
        miembros.setRowFactory(tableView -> {
            TableRow<MiembroMesaElectoral> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    MiembroMesaElectoral rowData = row.getItem();
                    //
                    System.out.println(rowData.getPrimerNombre());
                }
            });
            return row ;
        });
    }

    public void setApplicationController(ApplicationController controller) {
        applicationController = controller;
    }

    public void performQuery(String query) {
        miembros.setItems(miembroData.findAll(query.toUpperCase()));
    }
}
