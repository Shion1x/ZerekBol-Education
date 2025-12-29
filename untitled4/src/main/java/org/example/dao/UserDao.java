package org.example.dao;

import org.example.db.Database;
import org.example.model.User;
import org.example.util.HashUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    public User findByUsername(String username) throws Exception {
        String sql = "SELECT id, username FROM users WHERE username = ?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return new User(rs.getLong("id"), rs.getString("username"));
                return null;
            }
        }
    }

    public User login(String username, String password) throws Exception {
        String sql = "SELECT id, username FROM users WHERE username = ? AND password_hash = ?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username.trim());
            ps.setString(2, HashUtil.sha256(password));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return new User(rs.getLong("id"), rs.getString("username"));
                return null;
            }
        }
    }

    public boolean register(String username, String password) throws Exception {
        String sql = "INSERT INTO users(username, password_hash) VALUES (?, ?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username.trim());
            ps.setString(2, HashUtil.sha256(password));
            return ps.executeUpdate() == 1;
        }
    }
}
