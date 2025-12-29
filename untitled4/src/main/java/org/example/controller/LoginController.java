package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.dao.UserDao;
import org.example.model.User;
import org.example.config.AppState;
import org.example.util.SceneManager;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private final UserDao userDao = new UserDao();

    @FXML
    private void onLogin() {
        messageLabel.setText("");

        String u = usernameField.getText();
        String p = passwordField.getText();

        if (u == null || u.isBlank() || p == null || p.isBlank()) {
            messageLabel.setText("Заполни логин и пароль.");
            return;
        }

        try {
            User user = userDao.login(u, p);

            if (user == null) {
                messageLabel.setText("Неверный логин или пароль.");
                return;
            }

            // ✅ сохраняем в AppState
            AppState.currentUsername = user.getUsername();
            AppState.currentUserId = user.getId();

            SceneManager.show("home.fxml", "EduPro — Home");

        } catch (Exception e) {
            e.printStackTrace(); // оставь, полезно
            messageLabel.setText("Ошибка подключения к БД.");
        }
    }

    @FXML
    private void onGoRegister() {
        SceneManager.show("register.fxml", "EduPro — Register");
    }
}
