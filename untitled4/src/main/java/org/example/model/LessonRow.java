package org.example.model;

public class LessonRow {
    private final int id;
    private final int orderNo;
    private final String title;
    private final String content;
    private final String status; // NOT_STARTED / IN_PROGRESS / DONE

    public LessonRow(int id, int orderNo, String title, String content, String status) {
        this.id = id;
        this.orderNo = orderNo;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public int getId() { return id; }
    public int getOrderNo() { return orderNo; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getStatus() { return status; }
}
