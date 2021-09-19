package com.example.demo.service;

import com.example.demo.entity.TaskEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exeptions.TaskNotFoundException;
import com.example.demo.exeptions.UserAlreadyExistException;
import com.example.demo.exeptions.UserNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepo;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UserRepo userRepo;

    public Task addTask(TaskEntity taskEntity, Long userId) throws UserNotFoundException {
        Optional<UserEntity> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User does not exist");
        }
        taskEntity.setUser(user.get());
        return Task.toModel(taskRepo.save(taskEntity));
    }

    public Task updateTask(Long taskId) throws TaskNotFoundException {
        Optional<TaskEntity> taskEntity = taskRepo.findById(taskId);
        if (taskEntity.isEmpty()) {
            throw new TaskNotFoundException("Task not found");
        }
        TaskEntity task = taskEntity.get();
        task.setCompleted(!task.getCompleted());
        return Task.toModel(taskRepo.save(task));
    }

}
