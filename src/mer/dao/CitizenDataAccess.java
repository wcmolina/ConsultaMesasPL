package mer.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mer.models.Citizen;
import mer.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class CitizenDataAccess {

    private final Connection connection;
    private ObservableList<Citizen> data;
    private PreparedStatement statement;

    public CitizenDataAccess() {
        connection = DbUtil.getConnection();
    }

    public ObservableList<Citizen> findAll(String query) {
        data = FXCollections.observableArrayList();
        try {
            statement = connection.prepareStatement("" +
                    "SELECT miembro.*, lugar.nombre, mesa.numero FROM MiembrosMer miembro " +
                    "INNER JOIN LugaresPoblados lugar ON miembro.lugarPobladoId = lugar.lugarPobladoId " +
                    "LEFT JOIN MesasElectorales mesa ON miembro.mesaElectoralId = mesa.mesaElectoralId " +
                    "WHERE miembro.primerNombre || ' ' || miembro.primerApellido LIKE ?" +
                    "OR lugar.nombre LIKE ?" +
                    "OR miembro.numeroIdentidad = ?");

            statement.setString(1, "%"+query+"%");
            statement.setString(2, "%"+query+"%");
            statement.setString(3, query);
            ResultSet result = statement.executeQuery();
            fetchRowData(result);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ObservableList<Citizen> find(int id) {
        data = FXCollections.observableArrayList();
        try {
            statement = connection.prepareStatement("" +
                    "SELECT miembro.*, lugares.nombre, mesa.numero FROM MiembrosMer miembro " +
                    "INNER JOIN LugaresPoblados lugar ON miembro.lugarPobladoId = lugar.lugarPobladoId " +
                    "LEFT JOIN MesasElectorales mesa ON miembro.mesaElectoralId = mesa.mesaElectoralId " +
                    "WHERE miembro.miembroMerId = ?");

            statement.setString(1, String.valueOf(id));
            ResultSet result = statement.executeQuery();
            fetchRowData(result);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void fetchRowData(ResultSet result) throws SQLException {
        while (result.next()) {
            Citizen citizen = new Citizen();
            citizen.setId(Integer.parseInt(result.getString("miembroMerId")));
            citizen.setPrimerNombre((result.getString("primerNombre")));
            citizen.setSegundoNombre((result.getString("segundoNombre")));
            citizen.setPrimerApellido((result.getString("primerApellido")));
            citizen.setSegundoApellido((result.getString("segundoApellido")));
            citizen.setNumeroIdentidad(result.getString("numeroIdentidad"));
            citizen.setFechaNacimiento(new SimpleDateFormat(result.getString("fechaNacimiento")));
            citizen.setDireccion(result.getString("nombre"));
            citizen.setNumeroMesa(result.getString("numero"));
            data.add(citizen);
        }
        result.close();
    }
}
