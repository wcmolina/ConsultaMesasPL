package mer.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MiembroMer {

    private SimpleStringProperty nombre = new SimpleStringProperty();
    private SimpleIntegerProperty identidad = new SimpleIntegerProperty();
    private SimpleStringProperty direccion = new SimpleStringProperty();

    public String getNombre() {
        return nombre.get();
    }

    public int getIdentidad() {
        return identidad.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setIdentidad(int identidad) {
        this.identidad.set(identidad);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }
}
