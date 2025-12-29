package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final int id;
    private final String title;
    private final String subtitle;
    private final List<Lesson> lessons = new ArrayList<>();

    public Course(int id, String title, String subtitle) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public List<Lesson> getLessons() { return lessons; }

    public int getTotalLessons() { return lessons.size(); }

    public int getDoneLessons() {
        return (int) lessons.stream().filter(l -> l.getStatus() == LessonStatus.DONE).count();
    }

    public double getProgress() {
        if (lessons.isEmpty()) return 0;
        return (double) getDoneLessons() / lessons.size();
    }
}
