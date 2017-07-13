package MER.controllers;

import MER.models.MiembroMer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
        //mover esta logica para evitar abrir y cerrar la conexion a la DB cada vez que se llama esta funcion
        Connection connection = null;
        Statement statement = null;
        ObservableList<MiembroMer> data = FXCollections.observableArrayList();
        try {
            System.out.println("Conecting to database...");
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:sample_db/chinook.db");

            System.out.println("Connected successfully");
            statement = connection.createStatement();

            System.out.printf("Performing query...%n%n");
            String sql = "SELECT * FROM customers;";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                MiembroMer representante = new MiembroMer();
                representante.setIdentidad((Integer.parseInt(result.getString("CustomerId"))));
                representante.setNombre((result.getString("FirstName")));
                data.add(representante);
            }
            //closing connection
            result.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }
}
