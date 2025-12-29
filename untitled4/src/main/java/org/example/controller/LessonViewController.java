package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.config.AppState;
import org.example.dao.LessonDao;
import org.example.model.LessonRow;
import org.example.util.SceneManager;

public class LessonViewController {

    @FXML private Label titleLabel;
    @FXML private Label statusLabel;
    @FXML private Label contentLabel;

    private final LessonDao lessonDao = new LessonDao();

    @FXML
    private void initialize() {
        try {
            // ставим IN_PROGRESS когда урок открыт
            if (AppState.selectedLessonId > 0) {
                lessonDao.setLessonStatus(AppState.currentUserId, AppState.selectedLessonId, "IN_PROGRESS");
            }

            LessonRow l = lessonDao.getLessonById(AppState.selectedLessonId, AppState.currentUserId);

            if (l == null) {
                titleLabel.setText("Урок не найден");
                statusLabel.setText("");
                contentLabel.setText("");
                return;
            }

            titleLabel.setText(l.getOrderNo() + ". " + l.getTitle());
            statusLabel.setText("Статус: " + l.getStatus());
            contentLabel.setText(l.getContent());

        } catch (Exception e) {
            e.printStackTrace();
            titleLabel.setText("Ошибка загрузки урока");
            statusLabel.setText("");
            contentLabel.setText("");
        }
    }

    @FXML
    private void onMarkDone() {
        try {
            lessonDao.setLessonStatus(AppState.currentUserId, AppState.selectedLessonId, "DONE");
            statusLabel.setText("Статус: DONE ✅");
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Ошибка: не удалось отметить урок");
        }
    }

    @FXML
    private void onBack() {
        SceneManager.show("lessons.fxml", "EduPro — Lessons");
    }
}
