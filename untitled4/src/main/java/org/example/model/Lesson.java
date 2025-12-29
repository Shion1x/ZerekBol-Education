package org.example.model;

public class Lesson {
    private final int id;
    private final String title;
    private LessonStatus status;

    public Lesson(int id, String title, LessonStatus status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public LessonStatus getStatus() { return status; }
    public void setStatus(LessonStatus status) { this.status = status; }
}
