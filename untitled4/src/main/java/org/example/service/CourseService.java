package org.example.service;

import org.example.model.Course;
import org.example.model.Lesson;
import org.example.model.LessonStatus;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    public List<Course> getCoursesForUser(int userId) {
        // позже заменим на запросы из MySQL
        List<Course> list = new ArrayList<>();

        Course c1 = new Course(1, "Введение в Data Science", "С финальной работой");
        c1.getLessons().add(new Lesson(1, "1. Введение в курс", LessonStatus.DONE));
        c1.getLessons().add(new Lesson(2, "2. Business Understanding", LessonStatus.DONE));
        c1.getLessons().add(new Lesson(3, "3. Data Understanding. Excel", LessonStatus.IN_PROGRESS));
        c1.getLessons().add(new Lesson(4, "4. Введение в Python", LessonStatus.NOT_STARTED));

        Course c2 = new Course(2, "Data Analyst. Junior", "С итоговым проектом");
        c2.getLessons().add(new Lesson(1, "1. Старт", LessonStatus.NOT_STARTED));
        c2.getLessons().add(new Lesson(2, "2. SQL основы", LessonStatus.NOT_STARTED));

        list.add(c1);
        list.add(c2);

        return list;
    }

    public Course getCourseById(int id) {
        return getCoursesForUser(1).stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
}
