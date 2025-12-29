package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.config.AppState;
import org.example.util.SceneManager;

public class HomeController {

    public static String CURRENT_USERNAME = "User";

    @FXML private Label helloLabel;

    @FXML
    private void initialize() {
        helloLabel.setText(CURRENT_USERNAME + ", добрый вечер!");
    }

    @FXML public void goHome()     { SceneManager.show("home.fxml", "EduPro — Home"); }
    @FXML public void goCourses()  { SceneManager.show("courses.fxml", "EduPro — Courses"); }
    @FXML public void goProfile()  { SceneManager.show("profile.fxml", "EduPro — Profile"); }
    @FXML public void goSettings() { SceneManager.show("settings.fxml", "EduPro — Settings"); }

    @FXML public void openCourseDS() {
        AppState.selectedCourseId = 1;
        SceneManager.show("lessons.fxml", "EduPro — Lessons");
    }

    @FXML public void openCourseJava() {
        AppState.selectedCourseId = 2;
        SceneManager.show("lessons.fxml", "EduPro — Lessons");
    }

    @FXML
    public void onLogout() {
        CURRENT_USERNAME = "User";
        AppState.currentUserId = 0;
        AppState.selectedCourseId = 0;
        AppState.selectedLessonId = -1;
        SceneManager.show("login.fxml", "EduPro — Login");
    }
}
