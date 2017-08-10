package mer.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mer.models.Citizen;
import mer.util.DbUtil;
import mer.util.FileUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class CitizenDataAccess {
    private final Connection CONNECTION;

    public CitizenDataAccess() {
        CONNECTION = DbUtil.getConnection();
    }

    public ObservableList<Citizen> findAll() {
        ObservableList<Citizen> data = null;
        FileUtils fileUtils = new FileUtils();
        try {
            String query = fileUtils.readFileToString(new File("queries/citizens.sql"));
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

    private ObservableList<Citizen> buildResultList(ResultSet result) throws SQLException {
        ObservableList<Citizen> resultData = FXCollections.observableArrayList();
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
            resultData.add(citizen);
        }
        return resultData;
    }
}
