package mer.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;

public class Citizen {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty primerNombre = new SimpleStringProperty();
    private SimpleStringProperty segundoNombre = new SimpleStringProperty();
    private SimpleStringProperty primerApellido = new SimpleStringProperty();
    private SimpleStringProperty segundoApellido = new SimpleStringProperty();
    private SimpleStringProperty numeroIdentidad = new SimpleStringProperty();
    private SimpleDateFormat fechaNacimiento = new SimpleDateFormat();
    private SimpleStringProperty direccion = new SimpleStringProperty();
    private SimpleStringProperty numeroMesa = new SimpleStringProperty();
    public static final String[] TABLE_COLUMNS = {"primerNombre", "primerApellido", "numeroIdentidad", "direccion", "numeroMesa"};

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getPrimerNombre() {
        return primerNombre.get();
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre.set(primerNombre);
    }

    public String getSegundoNombre() {
        return segundoNombre.get();
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre.set(segundoNombre);
    }

    public String getPrimerApellido() {
        return primerApellido.get();
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido.set(primerApellido);
    }

    public String getSegundoApellido() {
        return segundoApellido.get();
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido.set(segundoApellido);
    }

    public String getNumeroIdentidad() {
        return numeroIdentidad.get();
    }

    public void setNumeroIdentidad(String numeroIdentidad) {
        this.numeroIdentidad.set(numeroIdentidad);
    }

    public SimpleDateFormat getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(SimpleDateFormat fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getNumeroMesa() {
        return numeroMesa.get();
    }

    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa.set(numeroMesa);
    }

    public String getNombreCompleto() {
        return String.format("%s %s %s %s",
                primerNombre.get(),
                (segundoNombre.get() == null) ? "" : segundoNombre.get(),
                primerApellido.get(),
                (segundoApellido.get() == null) ? "" : segundoApellido.get()
        );
    }
}
