package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.example.util.SceneManager;

import java.util.ArrayList;
import java.util.List;

public class CourseController {

    // позже будем передавать реальный курс из Home (через SceneManager + параметры)
    private final String courseTitle = "Введение в Data Science";

    @FXML private Label courseTitleLabel;
    @FXML private Label courseMetaLabel;
    @FXML private Label progressLabel;
    @FXML private VBox lessonsBox;

    private enum Status {
        NOT_STARTED("○", "Не начато", "status-gray"),
        IN_PROGRESS("⏸", "В процессе", "status-orange"),
        DONE("✓", "Просмотрено", "status-green");

        final String icon;
        final String text;
        final String css;

        Status(String icon, String text, String css) {
            this.icon = icon;
            this.text = text;
            this.css = css;
        }
    }

    private static class Lesson {
        final int number;
        final String title;
        Status status;

        Lesson(int number, String title, Status status) {
            this.number = number;
            this.title = title;
            this.status = status;
        }
    }

    private final List<Lesson> lessons = new ArrayList<>();

    @FXML
    private void initialize() {
        courseTitleLabel.setText(courseTitle);
        courseMetaLabel.setText("5 уроков • тесты позже добавим");

        // демо-данные (потом возьмём из БД)
        lessons.add(new Lesson(1, "Введение в курс", Status.DONE));
        lessons.add(new Lesson(2, "Business Understanding", Status.DONE));
        lessons.add(new Lesson(3, "Data Understanding. Excel", Status.IN_PROGRESS));
        lessons.add(new Lesson(4, "Введение в Python", Status.DONE));
        lessons.add(new Lesson(5, "Переменные и типы данных", Status.NOT_STARTED));

        renderLessons();
        updateProgress();
    }

    private void renderLessons() {
        lessonsBox.getChildren().clear();

        for (Lesson lesson : lessons) {
            lessonsBox.getChildren().add(createLessonRow(lesson));
        }
    }

    private HBox createLessonRow(Lesson lesson) {
        HBox row = new HBox(12);
        row.getStyleClass().add("lesson-row");
        row.setMinHeight(54);

        Label num = new Label(lesson.number + ".");
        num.getStyleClass().add("lesson-num");

        Label title = new Label(lesson.title);
        title.getStyleClass().add("lesson-title");
        title.setWrapText(true);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label status = new Label(lesson.status.icon + " " + lesson.status.text);
        status.getStyleClass().addAll("lesson-status", lesson.status.css);

        Button open = new Button("Открыть");
        open.getStyleClass().add("primary-btn");
        open.setOnAction(e -> {
            // позже откроем lesson.fxml и передадим lessonId
            // сейчас просто пометим как "в процессе", если было "не начато"
            if (lesson.status == Status.NOT_STARTED) {
                lesson.status = Status.IN_PROGRESS;
                renderLessons();
                updateProgress();
            }
            // SceneManager.show("lesson.fxml", "EduPro — Урок"); // сделаем следующим шагом
        });

        row.getChildren().addAll(num, title, spacer, status, open);
        return row;
    }

    private void updateProgress() {
        long done = lessons.stream().filter(l -> l.status == Status.DONE).count();
        progressLabel.setText("Пройдено: " + done + " / " + lessons.size());
    }

    @FXML
    private void onBack() {
        SceneManager.show("home.fxml", "EduPro — Home");
    }

    @FXML
    private void onHome() {
        SceneManager.show("home.fxml", "EduPro — Home");
    }
}
