package mer.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
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
        //colDomicilio.setCellValueFactory(new PropertyValueFactory<MiembroMer, String>("direccion"));
        //agregar on mouse clicked event to each row in the TableView
        tablaMiembrosMer.setRowFactory(tableView -> {
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

        //focus on results table
        Platform.runLater( () -> tablaMiembrosMer.requestFocus() );
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
                preparedStatement = connection.prepareStatement("SELECT * FROM Municipios WHERE nombre LIKE ? OR nombreAscii LIKE ?");
                preparedStatement.setString(1, "%"+search.toUpperCase()+"%");
                preparedStatement.setString(2, "%"+search.toUpperCase()+"%");
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    MiembroMer miembro = new MiembroMer();
                    miembro.setIdentidad((Integer.parseInt(result.getString("municipioId"))));
                    miembro.setNombre((result.getString("nombre")));
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
