package mer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/Main.fxml"));
        stage.setTitle("Consulta de mesas electorales - Partido Liberal");
        stage.setScene(new Scene(root, 950, 650));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        //control when to exit application
        //Platform.setImplicitExit(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
