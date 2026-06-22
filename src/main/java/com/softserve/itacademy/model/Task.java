package com.softserve.itacademy.model;

import java.util.Objects;

public class Task {

    private final int id;
    private String title;
    private Priority priority;

    private static int counter = 1;

    public Task() {
        id = counter++;
    }

    public Task(String title, Priority priority) {
        this.title = title;
        this.priority = priority;
        id = counter++;
    }

    public Task(String title, Priority priority, int id) {
        this.title = title;
        this.priority = priority;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(!(obj instanceof Task other)) return false;

        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
