package mer.dao;

import mer.models.Entity;
import mer.models.MesaElectoral;
import mer.util.DbUtil;
import mer.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MesaElectoralDAO {
    private final Connection CONNECTION;

    public MesaElectoralDAO() {
        CONNECTION = DbUtil.getConnection();
    }

    /**
     * @param query a String containing the query
     * @return
     */
    public ObservableList<Entity> find(String query) {
        if (query.isEmpty()) return findAll();
        return null;
    }

    /**
     * @return
     */
    private ObservableList<Entity> findAll() {
        ObservableList<Entity> data = null;
        FileUtils fileUtils = new FileUtils();
        try {
            String query = fileUtils.readFileToString(new File("queries/mesas.sql"));
            //Execute query
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            data = resultToList(result);
            result.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * @param result the ResultSet obtained by executing a query
     * @return an ObservableList<Entity> containing a list of Entity objects
     * created from each entry in the result set
     * @throws SQLException if a database access error occurs or this method is
     *                      called on a closed result set
     */
    private ObservableList<Entity> resultToList(ResultSet result) throws SQLException {
        ObservableList<Entity> resultList = FXCollections.observableArrayList();
        while (result.next()) {
            MesaElectoral mesa = new MesaElectoral();
            mesa.setId(Integer.parseInt(result.getString("mesaElectoralId")));
            mesa.setDepartamento(result.getString("departamento"));
            mesa.setMunicipio(result.getString("municipio"));
            mesa.setSectorElectoral(result.getString("sector"));
            mesa.setCentroVotacion(result.getString("centro"));
            mesa.setNumero(result.getString("numero"));
            resultList.add(mesa);
        }
        return resultList;
    }
}
