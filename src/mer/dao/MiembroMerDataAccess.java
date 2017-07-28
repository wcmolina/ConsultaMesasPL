package mer.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mer.models.MiembroMer;
import mer.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class MiembroMerDataAccess {

    private final Connection connection;
    private ObservableList<MiembroMer> data;
    private PreparedStatement statement;

    public MiembroMerDataAccess() {
        connection = DbUtil.getConnection();
    }

    public ObservableList<MiembroMer> findAll(String query) {
        data = FXCollections.observableArrayList();
        try {
            statement = connection.prepareStatement("" +
                    "SELECT miembros.*, lugares.nombre, mesas.numero FROM MiembrosMer miembros " +
                    "INNER JOIN LugaresPoblados lugares ON miembros.lugarPobladoId = lugares.lugarPobladoId " +
                    "LEFT JOIN MesasElectorales mesas ON miembros.mesaElectoralId = mesas.mesaElectoralId " +
                    "WHERE primerNombre || ' ' || primerApellido LIKE ? OR miembros.numeroIdentidad = ?");

            statement.setString(1, "%"+query+"%");
            statement.setString(2, "%"+query+"%");
            ResultSet result = statement.executeQuery();
            fetchRowData(result);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ObservableList<MiembroMer> find(int id) {
        data = FXCollections.observableArrayList();
        try {
            statement = connection.prepareStatement("" +
                    "SELECT miembros.*, lugares.nombre, mesas.numero FROM MiembrosMer miembros " +
                    "INNER JOIN LugaresPoblados lugares ON miembros.lugarPobladoId = lugares.lugarPobladoId " +
                    "LEFT JOIN MesasElectorales mesas ON miembros.mesaElectoralId = mesas.mesaElectoralId " +
                    "WHERE miembros.miembroMerId = ?");

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
    }
}
