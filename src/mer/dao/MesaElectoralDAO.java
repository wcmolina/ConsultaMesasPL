package mer.dao;

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
    private final Connection CONEXION;

    public MesaElectoralDAO() {
        CONEXION = DbUtil.getConnection();
    }

    public ObservableList<MesaElectoral> buscarMesasDonde() {
        ObservableList<MesaElectoral> data = null;
        FileUtils fileUtils = new FileUtils();
        try {
            String consulta = fileUtils.readFileToString(new File("queries/mesas.sql"));
            //Execute query
            PreparedStatement statement = CONEXION.prepareStatement(consulta);
            ResultSet resultado = statement.executeQuery();
            data = crearListaDesdeResultado(resultado);
            resultado.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private ObservableList<MesaElectoral> crearListaDesdeResultado(ResultSet result) throws SQLException {
        ObservableList<MesaElectoral> listaResultado = FXCollections.observableArrayList();
        while (result.next()) {
            MesaElectoral mesa = new MesaElectoral();
            mesa.setId(Integer.parseInt(result.getString("mesaElectoralId")));
            mesa.setDepartamento(result.getString("departamento"));
            mesa.setMunicipio(result.getString("municipio"));
            mesa.setSectorElectoral(result.getString("sector"));
            mesa.setCentroVotacion(result.getString("centro"));
            mesa.setNumero(result.getString("numero"));
            listaResultado.add(mesa);
        }
        return listaResultado;
    }
}
