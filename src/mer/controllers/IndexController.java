package mer.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import mer.models.MiembroMer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import mer.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndexController {
    @FXML private TextField consulta;
    @FXML private SplitMenuButton buscar;
    @FXML private TableView<MiembroMer> tablaMiembrosMer;
    @FXML private TableColumn<MiembroMer, String> colIdentidad;
    @FXML private TableColumn<MiembroMer, String> colNombre;
    @FXML private TableColumn<MiembroMer, String> colDomicilio;

    private Connection connection = DbUtil.getConnection();

    @FXML
    public void initialize() {
        //especificar como debe llenarse cada TableColumn a partir de una propiedad de un POJO (Plain Old Java Object)
        colIdentidad.setCellValueFactory(new PropertyValueFactory<MiembroMer, String>("identidad"));
        colNombre.setCellValueFactory(new PropertyValueFactory<MiembroMer, String>("nombre"));
    }

    @FXML
    public void changeSearchBy(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        buscar.setText(item.getText());
    }

    @FXML
    public void performQuery(ActionEvent event) {
        ObservableList<MiembroMer> data = FXCollections.observableArrayList();
        String search = consulta.getText();
        if (!search.isEmpty()) {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE FirstName = ? COLLATE NOCASE");
                preparedStatement.setString(1, search);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    MiembroMer miembro = new MiembroMer();
                    miembro.setIdentidad((Integer.parseInt(result.getString("CustomerId"))));
                    miembro.setNombre((result.getString("FirstName")));
                    data.add(miembro);
                }
                result.close();
                preparedStatement.close();
                //display results in the TableView
                tablaMiembrosMer.setItems(data);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
    }
}
