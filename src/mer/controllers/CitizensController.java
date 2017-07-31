package mer.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import mer.dao.CitizenDataAccess;
import mer.models.Citizen;
import org.controlsfx.control.MasterDetailPane;
import java.io.IOException;

public class CitizensController {
    public MasterDetailPane masterDetail;
    public TableView citizens;
    public TextField citizenQuery;
    public VBox citizenDetails;
    public ComboBox filterCitizensBy;
    public Button searchCitizen;
    private CitizenDataAccess citizenDataAccess = new CitizenDataAccess();

    public void initialize() {
        System.out.println("Init citizens");
        //Distribute column width and set cell value factories
        ObservableList<TableColumn> columns = citizens.getColumns();
        citizens.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).setCellValueFactory(new PropertyValueFactory(Citizen.TABLE_COLUMNS[i]));
        }

    }

    public Node getCitizenDetails(TableRow<Citizen> row) {
        try {
            Node details;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mer/views/CitizenDetails.fxml"));
            details = loader.load();
            CitizenDetailsController controller = loader.getController();
            controller.nombreCompleto.setText(row.getItem().getNombreCompleto());
            controller.fechaNacimiento.setText(row.getItem().getFechaNacimiento().toLocalizedPattern());
            controller.numeroMesa.setText(row.getItem().getNumeroMesa());
            controller.removeTable.setOnAction(event -> {
                System.out.println("Eliminando asociacion");
                //param.toggleExpanded();
            });
            return details;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void performQuery() {
        if (!citizenQuery.getText().isEmpty()) {
            citizens.setItems(citizenDataAccess.findAll(citizenQuery.getText()));
        }
    }
}
