package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.dao.UserDao;
import org.example.util.SceneManager;

public class RegisterController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField passwordField2;
    @FXML private Label messageLabel;

    private final UserDao userDao = new UserDao();

    @FXML
    private void onRegister() {
        messageLabel.setText("");

        String u = usernameField.getText();
        String p1 = passwordField.getText();
        String p2 = passwordField2.getText();

        if (u == null || u.isBlank() || p1 == null || p1.isBlank() || p2 == null || p2.isBlank()) {
            messageLabel.setText("Заполни все поля.");
            return;
        }
        if (u.length() < 3) {
            messageLabel.setText("Логин минимум 3 символа.");
            return;
        }
        if (!p1.equals(p2)) {
            messageLabel.setText("Пароли не совпадают.");
            return;
        }
        if (p1.length() < 6) {
            messageLabel.setText("Пароль минимум 6 символов.");
            return;
        }

        try {
            if (userDao.findByUsername(u) != null) {
                messageLabel.setText("Такой логин уже существует.");
                return;
            }
            boolean ok = userDao.register(u, p1);
            if (ok) {
                SceneManager.show("login.fxml", "EduPro — Login");
            } else {
                messageLabel.setText("Не удалось зарегистрировать.");
            }
        } catch (Exception e) {
            messageLabel.setText("Ошибка подключения к БД.");
        }
    }

    @FXML
    private void onBack() {
        SceneManager.show("login.fxml", "EduPro — Login");
    }
}
