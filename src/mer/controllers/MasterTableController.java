package mer.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mer.models.MiembroMer;
import mer.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MasterTableController {
    @FXML private TableView miembros;
    @FXML private TableColumn<MiembroMer, String> colNombre;
    @FXML private TableColumn<MiembroMer, String> colApellido;
    @FXML private TableColumn<MiembroMer, String> colIdentidad;
    @FXML private TableColumn<MiembroMer, String> colDomicilio;
    @FXML private TableColumn<MiembroMer, String> colMer;
    private Connection connection = DbUtil.getConnection();
    public MainController mainController;


    @FXML
    public void initialize() {
        System.out.println("Init master table");
        //same column size
        colNombre.prefWidthProperty().bind(miembros.widthProperty().multiply(0.20));
        colApellido.prefWidthProperty().bind(miembros.widthProperty().multiply(0.20));
        colIdentidad.prefWidthProperty().bind(miembros.widthProperty().multiply(0.20));
        colDomicilio.prefWidthProperty().bind(miembros.widthProperty().multiply(0.20));
        colMer.prefWidthProperty().bind(miembros.widthProperty().multiply(0.20));

        //cell value factories
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        //colApellido.setCellValueFactory(new PropertyValueFactory("primerApellido"));
        colIdentidad.setCellValueFactory(new PropertyValueFactory("identidad"));
        //colIdentidad.setCellValueFactory(new PropertyValueFactory("domicilio"));

        //double-click event to each row
        //agregar on mouse clicked event to each row in the TableView
        miembros.setRowFactory(tableView -> {
            TableRow<MiembroMer> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    MiembroMer rowData = row.getItem();
                    //works, but how?
                    System.out.println(rowData.getNombre());
                }
            });
            return row ;
        });
    }

    public void setMainController(MainController controller) {
        mainController = controller;
    }

    public void performQuery(String query) {
        ObservableList<MiembroMer> data = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM Municipios WHERE nombre LIKE ? OR nombreAscii LIKE ?");
            preparedStatement.setString(1, "%"+query.toUpperCase()+"%");
            preparedStatement.setString(2, "%"+query.toUpperCase()+"%");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                MiembroMer miembro = new MiembroMer();
                miembro.setIdentidad((Integer.parseInt(result.getString("municipioId"))));
                miembro.setNombre((result.getString("nombre")));
                data.add(miembro);
            }
            result.close();
            preparedStatement.close();
            //display results in the table
            miembros.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
}
