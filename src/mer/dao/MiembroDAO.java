package mer.dao;

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
    private final Connection CONEXION;

    public MiembroDAO() {
        CONEXION = DbUtil.getConnection();
    }

    public ObservableList<Miembro> buscarTodos() {
        ObservableList<Miembro> data = null;
        FileUtils fileUtils = new FileUtils();
        try {
            String consulta = fileUtils.readFileToString(new File("queries/miembros.sql"));
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

    private ObservableList<Miembro> crearListaDesdeResultado(ResultSet result) throws SQLException {
        ObservableList<Miembro> listaResultado = FXCollections.observableArrayList();
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
            listaResultado.add(miembro);
        }
        return listaResultado;
    }
}
