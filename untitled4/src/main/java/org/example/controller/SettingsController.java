package org.example.controller;

import javafx.fxml.FXML;
import org.example.util.SceneManager;

public class SettingsController {

    @FXML public void goHome()     { SceneManager.show("home.fxml", "EduPro — Home"); }
    @FXML public void goCourses()  { SceneManager.show("courses.fxml", "EduPro — Courses"); }
    @FXML public void goProfile()  { SceneManager.show("profile.fxml", "EduPro — Profile"); }
    @FXML public void goSettings() { SceneManager.show("settings.fxml", "EduPro — Settings"); }

    @FXML public void onLogout() {
        HomeController.CURRENT_USERNAME = "User";
        SceneManager.show("login.fxml", "EduPro — Login");
    }
}
