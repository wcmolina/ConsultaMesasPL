package mer.controllers;

import mer.models.MiembroMer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mer.util.DbUtil;

import java.sql.*;

public class IndexController {
    @FXML private TextField consulta;
    @FXML private TableView<MiembroMer> tablaMiembrosMer;
    @FXML private TableColumn<MiembroMer, String> colIdentidad;
    @FXML private TableColumn<MiembroMer, String> colNombre;
    @FXML private TableColumn<MiembroMer, String> colDomicilio;

    @FXML
    public void initialize() {
        //especificar como debe llenarse cada TableColumn a partir de una propiedad de un POJO (Plain Old Java Object)
        colIdentidad.setCellValueFactory(new PropertyValueFactory<MiembroMer, String>("identidad"));
        colNombre.setCellValueFactory(new PropertyValueFactory<MiembroMer, String>("nombre"));

        //llenar la tabla con los datos (falta programar el boton 'consultar' para llenar la tabla a partir del query dado)
        tablaMiembrosMer.setItems(fetchData());
    }

    private ObservableList<MiembroMer> fetchData() {
        Statement statement = null;
        ObservableList<MiembroMer> data = FXCollections.observableArrayList();
        try {
            statement = DbUtil.getConnection().createStatement();
            String sql = "SELECT * FROM customers;";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                MiembroMer miembro = new MiembroMer();
                miembro.setIdentidad((Integer.parseInt(result.getString("CustomerId"))));
                miembro.setNombre((result.getString("FirstName")));
                data.add(miembro);
            }
            result.close();
            statement.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }
}
