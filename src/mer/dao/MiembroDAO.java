package mer.dao;

import mer.models.Entity;
import mer.models.Miembro;
import mer.util.DbUtil;
import mer.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MiembroDAO {
    private final Connection CONNECTION;

    public MiembroDAO() {
        CONNECTION = DbUtil.getConnection();
    }

    /**
     * @param query a String containing the query
     * @return
     */
    public ObservableList<Entity> find(String query) {
        if (query.isEmpty()) return findAll();
        ObservableList<Entity> data = null;
        return data;
    }

    /**
     * @return
     */
    private ObservableList<Entity> findAll() {
        ObservableList<Entity> data = null;
        FileUtils fileUtils = new FileUtils();
        try {
            String query = fileUtils.readFileToString(new File("queries/miembros.sql"));
            //Execute query
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            data = resultToList(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * @param   result the ResultSet obtained by executing a query
     * @return  an ObservableList<Entity> containing a list of Entity objects
     *          created from each entry in the result set
     * @throws  SQLException if a database access error occurs or this method is
     *          called on a closed result set
     */
    private ObservableList<Entity> resultToList(ResultSet result) throws SQLException {
        ObservableList<Entity> resultList = FXCollections.observableArrayList();
        while (result.next()) {
            Miembro miembro = new Miembro();
            miembro.setId(Integer.parseInt(result.getString("miembroMerId")));
            miembro.setPrimerNombre((result.getString("primerNombre")));
            miembro.setSegundoNombre((result.getString("segundoNombre")));
            miembro.setPrimerApellido((result.getString("primerApellido")));
            miembro.setSegundoApellido((result.getString("segundoApellido")));
            miembro.setNumeroIdentidad(result.getString("numeroIdentidad"));
            miembro.setFechaNacimiento(new SimpleDateFormat(result.getString("fechaNacimiento")));
            miembro.setDireccion(result.getString("nombre"));
            miembro.setNumeroMesa(result.getString("numero"));
            resultList.add(miembro);
        }
        return resultList;
    }
}
