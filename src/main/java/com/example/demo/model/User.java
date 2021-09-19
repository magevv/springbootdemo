package com.example.demo.model;

import com.example.demo.entity.UserEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class User {
    private long id;
    private String name;
    private List<Task> tasks;

    public User(long id, String name, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
    }

    public static User toModel(UserEntity userEntity) {
        List<Task> tasks = userEntity.getTasks().stream().map(Task::toModel).collect(Collectors.toList());
        return new User(userEntity.getId(), userEntity.getName(), tasks);
    }
}
