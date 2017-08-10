package mer.dao;

import mer.models.ElectoralTable;
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

public class ElectoralTableDataAccess {
    private final Connection CONNECTION;

    public ElectoralTableDataAccess() {
        CONNECTION = DbUtil.getConnection();
    }

    public ObservableList<ElectoralTable> filledTablesWhere() {
        ObservableList<ElectoralTable> data = null;
        FileUtils fileUtils = new FileUtils();
        try {
            String query = fileUtils.readFileToString(new File("queries/electoral_tables.sql"));
            //Execute query
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            data = buildResultList(result);
            result.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private ObservableList<ElectoralTable> buildResultList(ResultSet result) throws SQLException {
        ObservableList<ElectoralTable> data = FXCollections.observableArrayList();
        while (result.next()) {
            ElectoralTable table = new ElectoralTable();
            table.setId(Integer.parseInt(result.getString("mesaElectoralId")));
            table.setDepartamento(result.getString("departamento"));
            table.setMunicipio(result.getString("municipio"));
            table.setSectorElectoral(result.getString("sector"));
            table.setCentroVotacion(result.getString("centro"));
            table.setNumero(result.getString("numero"));
            data.add(table);
        }
        return data;
    }
}
