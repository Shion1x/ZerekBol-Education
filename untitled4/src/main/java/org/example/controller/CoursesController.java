package org.example.controller;

import javafx.fxml.FXML;
import org.example.config.AppState;
import org.example.util.SceneManager;

public class CoursesController {

    @FXML public void goHome()     { SceneManager.show("home.fxml", "EduPro — Home"); }
    @FXML public void goCourses()  { SceneManager.show("courses.fxml", "EduPro — Courses"); }
    @FXML public void goProfile()  { SceneManager.show("profile.fxml", "EduPro — Profile"); }
    @FXML public void goSettings() { SceneManager.show("settings.fxml", "EduPro — Settings"); }

    @FXML public void openDS() {
        AppState.selectedCourseId = 1;
        SceneManager.show("lessons.fxml", "EduPro — Lessons");
    }

    @FXML public void openJava() {
        AppState.selectedCourseId = 2;
        SceneManager.show("lessons.fxml", "EduPro — Lessons");
    }

    @FXML public void onLogout() {
        HomeController.CURRENT_USERNAME = "User";
        AppState.currentUserId = 0;
        AppState.selectedCourseId = 0;
        AppState.selectedLessonId = -1;
        SceneManager.show("login.fxml", "EduPro — Login");
    }
}
