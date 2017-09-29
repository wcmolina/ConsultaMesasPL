package mer.controllers;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import mer.App;
import mer.dao.MesaElectoralDAO;
import mer.dao.MiembroDAO;
import mer.events.QueryEvent;
import mer.models.Entity;
import mer.models.MesaElectoral;
import mer.models.Miembro;

public class IndexController {
    @FXML
    private TableView<Entity> mainTable;
    @FXML
    private AnchorPane detail;
    public MiembroInfoController detallesController;
    private MiembroDAO miembroDAO;
    private MesaElectoralDAO mesaElectoralDAO;

    public void initialize() {
        System.out.println("Init index");
        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        mainTable.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("Seleccionó un miembro mer o una mer");
                //detallesController.displayCitizenDetails((Miembro) resultado.getSelectionModel().getSelectedItem());
            }
        });
        // Data access objects
        miembroDAO = new MiembroDAO();
        mesaElectoralDAO = new MesaElectoralDAO();
        // Register this controller to the event bus
        App.EVENT_BUS.register(this);
    }

    @Subscribe
    public void processQuery(QueryEvent queryEvent) {
        String context = queryEvent.getContext();
        String query = queryEvent.getQuery();
        // Change table columns depending on the "context"
        if (context.equals("Miembros MER")) {
            showMembersTable();
            // Maybe pass to find() the search event containing all the search parameters
            mainTable.setItems(miembroDAO.find(query));
        } else if (context.equals("Mesas electorales")) {
            showMerTable();
            mainTable.setItems(mesaElectoralDAO.find(query));
        }
        // This seems to fix a bug where TableView columns are not aligned
        Platform.runLater(() -> mainTable.refresh());
    }

    private void showMembersTable() {
        mainTable.getColumns().clear();
        for (int i = 0; i < Miembro.TABLE_COLUMNS.length; i++) {
            TableColumn<Entity, String> column = new TableColumn<>();
            column.setText(Miembro.TABLE_COLUMNS[i]);
            column.setCellValueFactory(new PropertyValueFactory<>(Miembro.TABLE_PROPERTIES[i]));
            mainTable.getColumns().add(column);
        }
    }

    private void showMerTable() {
        mainTable.getColumns().clear();
        for (int i = 0; i < MesaElectoral.TABLE_COLUMNS.length; i++) {
            TableColumn<Entity, String> column = new TableColumn<>();
            column.setText(MesaElectoral.TABLE_COLUMNS[i]);
            column.setCellValueFactory(new PropertyValueFactory<>(MesaElectoral.TABLE_PROPERTIES[i]));
            mainTable.getColumns().add(column);
        }
    }
}
