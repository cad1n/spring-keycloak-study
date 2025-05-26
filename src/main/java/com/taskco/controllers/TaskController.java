package com.taskco.controllers;

import com.taskco.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.taskco.entity.Task;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAllTasks() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable Integer id) {
        return taskService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody Task task) {
        return taskService.findById(id)
            .map(existingTask -> {
                task.setId(id);
                return ResponseEntity.ok(taskService.saveTask(task));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        if (taskService.findById(id).isPresent()) {
            taskService.deleteTask(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}