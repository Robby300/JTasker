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

    public ToDo(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.name = builder.name;
        this.description = builder.description;
        this.createdOn = builder.createdOn;
        this.deadline = builder.deadline;
        this.isDone = builder.isDone;
        this.parentToDoId = builder.parentToDoId;
    }

    public static class Builder {

        private long id;
        private long userId;
        private String name;
        private String description;
        private LocalDateTime createdOn;
        private LocalDateTime deadline;
        private boolean isDone;
        private Long parentToDoId;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder createdOn(LocalDateTime createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public Builder deadline(LocalDateTime deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder isDone(Boolean isDone) {
            this.isDone = isDone;
            return this;
        }

        public Builder parentToDoId(long parentToDoId) {
            this.parentToDoId = parentToDoId;
            return this;
        }

        public ToDo build() {
            return new ToDo(this);
        }
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

    @Override
    public String toString() {
        return "ToDo{" +
                "id= " + id +
                ", userId= " + userId +
                ", name= " + name + '\'' +
                ", description= " + description + '\'' +
                ", createdOn= " + createdOn +
                ", deadline= " + deadline +
                ", isDone= " + isDone +
                ", parentToDoId= " + parentToDoId +
                '}';
    }
}
