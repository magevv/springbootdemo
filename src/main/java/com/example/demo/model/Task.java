package com.example.demo.model;

import com.example.demo.entity.TaskEntity;
import lombok.Data;

@Data
public class Task {
    private long id;
    private String title;
    private Boolean completed = false;

    public Task(long id, String title, Boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public static Task toModel(TaskEntity taskEntity) {
        return new Task(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getCompleted());
    }
}
