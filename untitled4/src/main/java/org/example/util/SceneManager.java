package org.example.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Main;

public final class SceneManager {

    private static Stage stage;

    private static double APP_W = 1100;
    private static double APP_H = 700;

    private SceneManager() {}

    /** Инициализация (вызывается один раз из Main) */
    public static void init(Stage primaryStage) {
        stage = primaryStage;
    }

    /** Установка размера приложения */
    public static void setAppSize(double width, double height) {
        APP_W = width;
        APP_H = height;

        if (stage != null) {
            stage.setWidth(APP_W);
            stage.setHeight(APP_H);
            stage.centerOnScreen();
        }
    }

    /** Показ сцены по FXML */
    public static void show(String fxml, String title) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(Main.class.getResource("/views/" + fxml));
            Parent root = loader.load();

            Scene scene = new Scene(root, APP_W, APP_H);
            scene.getStylesheets().add(
                    Main.class.getResource("/styles/style.css").toExternalForm()
            );

            stage.setTitle(title);
            stage.setScene(scene);

            // фиксируем размер
            stage.setWidth(APP_W);
            stage.setHeight(APP_H);
            stage.centerOnScreen();

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load scene: " + fxml, e);
        }
    }
}
