package mer.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ElectoralTable {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty departamento = new SimpleStringProperty();
    private SimpleStringProperty municipio = new SimpleStringProperty();
    private SimpleStringProperty sectorElectoral = new SimpleStringProperty();
    private SimpleStringProperty centroVotacion = new SimpleStringProperty();
    private SimpleStringProperty numero = new SimpleStringProperty();
    public static final String[] TABLE_PROPERTIES = {"departamento", "municipio", "sectorElectoral", "centroVotacion", "numero"};
    public static final String[] TABLE_COLUMNS = {"Departamento", "Municipio", "Sector Electoral", "Centro de votación", "Urna"};

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDepartamento() {
        return departamento.get();
    }

    public void setDepartamento(String departamento) {
        this.departamento.set(departamento);
    }

    public String getMunicipio() {
        return municipio.get();
    }

    public void setMunicipio(String municipio) {
        this.municipio.set(municipio);
    }

    public String getSectorElectoral() {
        return sectorElectoral.get();
    }

    public void setSectorElectoral(String sectorElectoral) {
        this.sectorElectoral.set(sectorElectoral);
    }

    public String getCentroVotacion() {
        return centroVotacion.get();
    }

    public void setCentroVotacion(String centroVotacion) {
        this.centroVotacion.set(centroVotacion);
    }

    public String getNumero() {
        return numero.get();
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
    }
}
