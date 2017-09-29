package mer.controllers;

import javafx.fxml.FXML;
import mer.models.Miembro;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class MiembroInfoController {
    @FXML
    private Hyperlink quitarMesa;
    @FXML
    private Hyperlink cambiarMesa;
    @FXML
    private Hyperlink editarMiembro;
    @FXML
    private TextField nombreCompleto;
    @FXML
    private TextField numeroMesa;
    @FXML
    private TextField fechaNacimiento;

    public void initialize() {
        System.out.println("Init citizen detail");
    }

    public void displayCitizenDetails(Miembro miembro) {
        nombreCompleto.setText(miembro.getNombreCompleto());
        fechaNacimiento.setText(miembro.getFechaNacimiento().toLocalizedPattern());
        numeroMesa.setText(miembro.getNumeroMesa());

        //Disable buttons here. If numeroMesa == null, disable quitarMesa and enable cambiarMesa
        quitarMesa.setDisable(miembro.getNumeroMesa() == null);
        cambiarMesa.setDisable(miembro.getNumeroMesa() != null);
    }

    public void removeCitizenTable(Miembro miembro) {
        //Show confirmation dialog: are you sure?
        //MiembroDAO.removeAssociatedTable(CitizenId)
        //quitarMesa: metodo que ejecuta query para quitar la mesa asociada a un miembro de mesa

        //una vez actualizada la base de datos, que pasa con la instancia que se creo a partir del registro ahora desactualizado?
    }

    public void clearTextFields() {
        nombreCompleto.setText("");
        numeroMesa.setText("");
        fechaNacimiento.setText("");
    }
}
