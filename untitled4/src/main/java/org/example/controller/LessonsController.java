package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.example.config.AppState;
import org.example.dao.LessonDao;
import org.example.model.LessonRow;
import org.example.util.SceneManager;

import java.util.List;

public class LessonsController {

    @FXML private VBox lessonsBox;
    @FXML private Label courseTitleLabel;

    private final LessonDao lessonDao = new LessonDao();

    @FXML
    private void initialize() {
        courseTitleLabel.setText("Курс #" + AppState.selectedCourseId);
        reload();
    }

    private void reload() {
        lessonsBox.getChildren().clear();

        try {
            // ВАЖНО: нужен СПИСОК уроков
            List<LessonRow> lessons = lessonDao.getLessonsWithStatus(
                    AppState.selectedCourseId,
                    AppState.currentUserId
            );

            if (lessons == null || lessons.isEmpty()) {
                Label empty = new Label("Нет уроков для этого курса.");
                empty.getStyleClass().add("muted");
                lessonsBox.getChildren().add(empty);
                return;
            }

            for (LessonRow l : lessons) {
                lessonsBox.getChildren().add(buildRow(l));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Label err = new Label("Ошибка загрузки уроков из БД.");
            err.getStyleClass().add("error");
            lessonsBox.getChildren().add(err);
        }
    }

    private HBox buildRow(LessonRow l) {
        Label title = new Label(l.getOrderNo() + ". " + l.getTitle());
        title.getStyleClass().add("lesson-title");

        Label status = new Label(statusText(l.getStatus()));
        status.getStyleClass().add("lesson-status");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox row = new HBox(12, title, spacer, status);
        row.getStyleClass().add("lesson-row");

        // кликабельная строка
        row.setOnMouseClicked(e -> openLesson(l.getId()));

        return row;
    }

    private String statusText(String s) {
        if (s == null) return "○ Не начато";
        return switch (s) {
            case "DONE" -> "✅ Просмотрено";
            case "IN_PROGRESS" -> "⏸ В процессе";
            default -> "○ Не начато";
        };
    }

    private void openLesson(int lessonId) {
        AppState.selectedLessonId = lessonId;
        SceneManager.show("lesson_view.fxml", "EduPro — Lesson");
    }

    @FXML
    private void onBack() {
        SceneManager.show("home.fxml", "EduPro — Home");
    }
}
