package org.example.dao;

import org.example.db.Database;
import org.example.model.LessonRow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LessonDao {

    // 1) Список уроков по курсу + статус пользователя
    public List<LessonRow> getLessonsWithStatus(int courseId, long userId) {
        String sql = """
            SELECT l.id, l.order_no, l.title, l.content,
                   COALESCE(lp.status, 'NOT_STARTED') AS status
            FROM lessons l
            LEFT JOIN lesson_progress lp
              ON lp.lesson_id = l.id AND lp.user_id = ?
            WHERE l.course_id = ?
            ORDER BY l.order_no
        """;

        List<LessonRow> list = new ArrayList<>();

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ps.setInt(2, courseId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new LessonRow(
                            rs.getInt("id"),
                            rs.getInt("order_no"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("status")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 2) Один урок по id + статус пользователя
    public LessonRow getLessonById(int lessonId, long userId) {
        String sql = """
            SELECT l.id, l.order_no, l.title, l.content,
                   COALESCE(lp.status, 'NOT_STARTED') AS status
            FROM lessons l
            LEFT JOIN lesson_progress lp
              ON lp.lesson_id = l.id AND lp.user_id = ?
            WHERE l.id = ?
            LIMIT 1
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ps.setInt(2, lessonId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new LessonRow(
                            rs.getInt("id"),
                            rs.getInt("order_no"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("status")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 3) Поставить статус урока (IN_PROGRESS / DONE / NOT_STARTED)
    public void setLessonStatus(long userId, int lessonId, String status) {
        String sql = """
            INSERT INTO lesson_progress (user_id, lesson_id, status)
            VALUES (?, ?, ?)
            ON DUPLICATE KEY UPDATE
              status = VALUES(status),
              updated_at = CURRENT_TIMESTAMP
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ps.setInt(2, lessonId);
            ps.setString(3, status);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ❌ УДАЛИ или оставь пустым, но лучше удалить:
    // public void getClass(...) {}
}
