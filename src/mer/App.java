package mer;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.MasterDetailPane;

import java.io.IOException;

public class App extends Application {
    public static final String SPLASH_IMAGE =
            "/mer/views/assets/splash.jpg";

    private Pane splashLayout;
    private static final int SPLASH_WIDTH = 700;
    private static final int SPLASH_HEIGHT = 259;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void init() {
        ImageView splash = new ImageView(new Image(
                SPLASH_IMAGE
        ));
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash);
        splashLayout.setStyle(
                "-fx-background-color: white; " +
                        "-fx-border-width:5; " +
                        "-fx-border-color: darkred"
        );
        splashLayout.setEffect(new DropShadow());
    }

    @Override
    public void start(final Stage initStage) throws Exception {
        showSplash(initStage);
    }

    private void showMainStage() {
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("views/Application.fxml"));
            //MasterDetailPane lookup = (MasterDetailPane) root.lookup("#masterDetail");
            //lookup.setMasterNode(FXMLLoader.load(getClass().getResource("views/Members.fxml")));
            //System.out.println("Node lookup: "+lookup);
            stage.setTitle("Consulta de mesas electorales - Partido Liberal");
            stage.setScene(new Scene(root, 950, 650));
            //stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSplash(final Stage initStage) {
        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        initStage.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            initStage.hide();
            showMainStage();
        });
        pause.play();
    }
}
