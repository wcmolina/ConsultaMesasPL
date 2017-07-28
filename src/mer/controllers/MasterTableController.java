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
import java.text.SimpleDateFormat;

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
        colNombre.setCellValueFactory(new PropertyValueFactory("primerNombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory("primerApellido"));
        colIdentidad.setCellValueFactory(new PropertyValueFactory("numeroIdentidad"));
        colDomicilio.setCellValueFactory(new PropertyValueFactory("direccion"));
        colMer.setCellValueFactory(new PropertyValueFactory("numeroMesa"));

        //double-click event to each row
        //agregar on mouse clicked event to each row in the TableView
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
        //move this logic to a Data Acces Object (dao)
        //MiembroMerDataAccess.all()
        //MiembroMerDataAccess.find(nombre o identidad)
        ObservableList<MiembroMer> data = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("" +
                    "SELECT miembros.*, lugares.nombre, mesas.numero " +
                    "FROM MiembrosMer miembros " +
                    "INNER JOIN LugaresPoblados lugares " +
                    "    ON miembros.lugarPobladoId = lugares.lugarPobladoId " +
                    "LEFT JOIN MesasElectorales mesas " +
                    "    ON miembros.mesaElectoralId = mesas.mesaElectoralId " +
                    "WHERE primerNombre || ' ' || primerApellido LIKE ? " +
                    "    OR miembros.numeroIdentidad = ?");
            preparedStatement.setString(1, "%"+query.toUpperCase()+"%");
            preparedStatement.setString(2, "%"+query.toUpperCase()+"%");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                MiembroMer miembro = new MiembroMer();
                miembro.setId(Integer.parseInt(result.getString("miembroMerId")));
                miembro.setPrimerNombre((result.getString("primerNombre")));
                miembro.setSegundoNombre((result.getString("segundoNombre")));
                miembro.setPrimerApellido((result.getString("primerApellido")));
                miembro.setSegundoApellido((result.getString("segundoApellido")));
                miembro.setNumeroIdentidad(result.getString("numeroIdentidad"));
                miembro.setFechaNacimiento(new SimpleDateFormat(result.getString("fechaNacimiento")));
                miembro.setDireccion(result.getString("nombre"));
                miembro.setNumeroMesa(result.getString("numero"));
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
