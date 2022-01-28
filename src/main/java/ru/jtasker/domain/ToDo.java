package ru.jtasker.domain;

import java.time.LocalDateTime;


public class ToDo {
    private long id;
    private long userId;
    private String name;
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime deadline;
    private boolean isDone;
    private Long parentToDoId;

    public ToDo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Long getParentToDoId() {
        return parentToDoId;
    }

    public void setParentToDoId(Long parentToDoId) {
        this.parentToDoId = parentToDoId;
    }
}
