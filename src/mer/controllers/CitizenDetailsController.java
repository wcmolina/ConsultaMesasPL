package mer.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import mer.models.Citizen;

public class CitizenDetailsController {
    public Button removeTable;
    public Button updateTable;
    public TextField nombreCompleto;
    public TextField numeroMesa;
    public TextField fechaNacimiento;

    public void initialize() {
        System.out.println("Init citizen details");
    }

    public void displayCitizenDetails(Citizen citizen) {
        nombreCompleto.setText(citizen.getNombreCompleto());
        fechaNacimiento.setText(citizen.getFechaNacimiento().toLocalizedPattern());
        numeroMesa.setText(citizen.getNumeroMesa());

        //Disable buttons here. If numeroMesa == null, disable removeTable and enable updateTable
        removeTable.setDisable(citizen.getNumeroMesa() == null);
        updateTable.setDisable(citizen.getNumeroMesa() != null);
    }

    public void removeCitizenTable(Citizen citizen) {
        //Show confirmation dialog: are you sure?
        //CitizenDataAccess.removeAssociatedTable(CitizenId)
        //removeTable: metodo que ejecuta query para quitar la mesa asociada a un miembro de mesa

        //una vez actualizada la base de datos, que pasa con la instancia que se creo a partir del registro ahora desactualizado?
    }

    public void clearTextFields() {
        nombreCompleto.setText("");
        numeroMesa.setText("");
        fechaNacimiento.setText("");
    }
}
