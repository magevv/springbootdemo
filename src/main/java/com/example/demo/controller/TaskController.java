package com.example.demo.controller;

import com.example.demo.entity.TaskEntity;
import com.example.demo.exeptions.TaskNotFoundException;
import com.example.demo.exeptions.UserNotFoundException;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity addTask(@RequestBody TaskEntity task,
                                  @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(taskService.addTask(task, userId));
        } catch(UserNotFoundException e) {
            return ResponseEntity.badRequest().body(String.format("User with id %d not found", userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }


    @PutMapping()
    public ResponseEntity updateTask(@RequestParam Long taskId) {
        try {
            return ResponseEntity.ok(taskService.updateTask(taskId));
        } catch(TaskNotFoundException e) {
            return ResponseEntity.badRequest().body(String.format("Task with id %d not found", taskId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
