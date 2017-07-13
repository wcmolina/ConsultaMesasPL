package MER.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Wilmer on 12/07/2017.
 */
public class MiembroMer {

    private SimpleStringProperty nombre = new SimpleStringProperty();
    private SimpleIntegerProperty identidad = new SimpleIntegerProperty();

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
}
