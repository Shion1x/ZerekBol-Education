package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.config.AppConfig;
import org.example.util.SceneManager;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager.init(stage);

        // один раз задаём размер всего приложения
        SceneManager.setAppSize(1100, 700);

        stage.setResizable(false);

        SceneManager.show(
                "login.fxml",
                AppConfig.get("app.title") + " — Login"
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
