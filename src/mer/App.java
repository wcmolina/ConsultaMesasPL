package mer;

import com.google.common.eventbus.EventBus;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.IOException;

public class App extends Application {
    public static final String SPLASH_IMAGE =
            "/mer/views/assets/splash.jpg";

    private Pane splashLayout;
    private static final int SPLASH_WIDTH = 700;
    private static final int SPLASH_HEIGHT = 259;
    public static final EventBus EVENT_BUS = new EventBus();

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void init() {
        ImageView image = new ImageView(new Image(SPLASH_IMAGE));
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(image);
    }

    @Override
    public void start(final Stage initStage) throws Exception {
        showSplash(initStage);
    }

    private void showMainStage() {
        Stage mainStage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("views/Index.fxml"));
            root.getStylesheets().add("/mer/views/assets/styles.css");
            mainStage.setTitle("Consulta de mesas electorales - Partido Liberal de Honduras");
            mainStage.setScene(new Scene(root, 1060, 660));
            mainStage.centerOnScreen();
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSplash(final Stage splash) {
        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        splash.setScene(splashScene);
        splash.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        splash.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        // Hides minimize, maximize, and close buttons
        splash.initStyle(StageStyle.TRANSPARENT);
        splash.setAlwaysOnTop(true);
        splash.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            splash.hide();
            showMainStage();
        });
        pause.play();
    }
}
