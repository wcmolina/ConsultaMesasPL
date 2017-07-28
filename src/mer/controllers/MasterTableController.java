package mer.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mer.models.MiembroMer;
import mer.dao.MiembroMerDataAccess;

public class MasterTableController {
    @FXML private TableView miembros;
    private MiembroMerDataAccess miembroData;
    public MainController mainController;


    @FXML
    public void initialize() {
        miembroData = new MiembroMerDataAccess();
        //must be in order of column appereance
        final String[] MIEMBRO_MER_PROPERTIES = {"primerNombre", "primerApellido", "numeroIdentidad", "direccion", "numeroMesa"};
        ObservableList<TableColumn> columns = miembros.getColumns();

        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).prefWidthProperty().bind(miembros.widthProperty().multiply(0.20));
            columns.get(i).setCellValueFactory(new PropertyValueFactory(MIEMBRO_MER_PROPERTIES[i]));
        }

        //double-click event to each row
        miembros.setRowFactory(tableView -> {
            TableRow<MiembroMer> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    MiembroMer rowData = row.getItem();
                    //works, but how?
                    System.out.println(rowData.getPrimerNombre());
                }
            });
            return row ;
        });
    }

    public void setMainController(MainController controller) {
        mainController = controller;
    }

    public void performQuery(String query) {
        miembros.setItems(miembroData.findAll(query.toUpperCase()));
    }
}
